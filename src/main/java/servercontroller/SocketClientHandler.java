/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.IDClientList;
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
    private IDClientList idClientList;

    public SocketClientHandler(Socket socket, InitializeAllPlay allPlay, IDClientList clientList) {
        this.socket = socket;
        this.allPlay = allPlay;
        this.player=null;
        this.idClientList=clientList;
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

            HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
            ActionState actionState = new ActionState(allPlay, stateHashMap,idClientList);
            EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
            MoveState moveState = new MoveState(allPlay, stateHashMap, idClientList);
            WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
            ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap, idClientList);
            ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap, idClientList);
            ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
            PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
            PowerupState powerupState = new PowerupState(allPlay, stateHashMap, idClientList);
            SpawnState spawnState = new SpawnState(allPlay, stateHashMap, idClientList);
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

            String game = (String) objectInputStream.readObject();
            if(game.equals("continue")){

            }
            else if(game.equals("new game")) {
                String nickname = (String) objectInputStream.readObject();
                idClientList.getNicknameList().add(nickname);

                allPlay.addPlayerCounter();
                player = serverManagerFunction.chooseCharacterManager(objectInputStream, this.allPlay, objectOutputStream, idClientList);
                while (true) {
                    if (allPlay.getPlayercountertemp() == 0) {
                        boolean ok = true;
                        objectOutputStream.writeObject(ok);
                        break;
                    }
                }
                idClientList.getNickPlayer().put(nickname, player);
                allPlay.getVoteMap().addPlayerCounter();
                serverManagerFunction.manageVoteMap(allPlay, outMessage, objectInputStream, objectOutputStream);
                while (true) {
                    if (allPlay.getStateSelectedMap().getSelectedmap() != null) {
                        boolean ok = true;
                        objectOutputStream.writeObject(ok);
                        break;
                    }
                }
                allPlay.resetPlayerCounterTemp();
                MessageString message = new MessageString("Map Selected: " + allPlay.getStateSelectedMap().getSelectedmap().getMapname() + "\n\n");
                objectOutputStream.writeObject(message);


                allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap);
                if (idClientList.getPlayerArrayList().get(0).equals(player)) {
                    allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.SPAWN));
                }

                Updater updater = new Updater(allPlay);
                UpdatePacket updatePacket = updater.updateClient(player);
                objectOutputStream.writeObject(updatePacket);

                Integer token = allPlay.getCurrentPlayerState().get(player).getToken();
                objectOutputStream.writeObject(token);

                StartGame startGame = new StartGame(allPlay, player, objectInputStream, objectOutputStream, stateHashMap, updater, idClientList);
                startGame.start();


                if (allPlay.isEndgame() == true) {
                    socket.close();
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
