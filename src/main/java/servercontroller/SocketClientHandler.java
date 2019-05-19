/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageString;
import model.modelstates.*;
import model.playerdata.Player;

import java.io.*;

import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class SocketClientHandler implements Runnable {
    private Socket socket;
    private InitializeAllPlay allPlay;
    private Player player;

    public SocketClientHandler(Socket socket, InitializeAllPlay allPlay) {
        this.socket = socket;
        this.allPlay = allPlay;
        this.player=null;
    }

    public void run() {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            PrintWriter outMessage = new PrintWriter(this.socket.getOutputStream());
            Scanner inMessage = new Scanner(socket.getInputStream());
            ServerManagerFunction serverManagerFunction = new ServerManagerFunction();
            allPlay.addPlayerCounter();

            player=serverManagerFunction.chooseCharacterManager(outMessage, objectInputStream, this.allPlay, objectOutputStream);
            while(true){
                if(allPlay.getPlayercountertemp()==0){
                    boolean ok = true;
                    objectOutputStream.writeObject(ok);
                    break;
                }
            }
            allPlay.getVoteMap().addPlayerCounter();
            serverManagerFunction.manageVoteMap(inMessage, allPlay, outMessage, objectInputStream, objectOutputStream);
            while(true){
                if(allPlay.getStateSelectedMap().getSelectedmap()!=null){
                    boolean ok = true;
                    objectOutputStream.writeObject(ok);
                    break;
                }
            }
            allPlay.resetPlayerCounterTemp();
            MessageString message = new MessageString("Map Selected: " + allPlay.getStateSelectedMap().getSelectedmap().getMapname() + "\n\n");
            objectOutputStream.writeObject(message);

            HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
            ActionState actionState = new ActionState(allPlay, stateHashMap);
            EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap);
            MoveState moveState = new MoveState(allPlay, stateHashMap);
            WaitingState waitingState = new WaitingState(allPlay, stateHashMap);
            ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap);
            ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap);
            ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap);
            PickUpState pickUpState = new PickUpState(allPlay, stateHashMap);
            PowerupState powerupState = new PowerupState(allPlay, stateHashMap);
            SpawnState spawnState = new SpawnState(allPlay, stateHashMap);
            stateHashMap.put(StatesEnum.ACTION, actionState);
            stateHashMap.put(StatesEnum.END, endTurnState);
            stateHashMap.put(StatesEnum.MOVE, moveState);
            stateHashMap.put(StatesEnum.WAIT, waitingState);
            stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
            stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
            stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
            stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
            stateHashMap.put(StatesEnum.POWERUP, powerupState);
            stateHashMap.put(StatesEnum.SPAWN, spawnState);

            allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap);
            if(allPlay.getIdClientList().getPlayerArrayList().get(0).equals(player)){
                allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.SPAWN));
            }

            UpdateThread updateThread = new UpdateThread(allPlay, player, objectOutputStream);
            updateThread.updateClient();


            StartGame startGame = new StartGame(allPlay, player, objectInputStream, objectOutputStream, stateHashMap, updateThread);
            startGame.start();


            if(allPlay.isEndgame()==true) {
                socket.close();
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
