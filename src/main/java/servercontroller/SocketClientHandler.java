/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageString;
import model.gamedata.Mode;
import model.map.Cell;
import model.map.Room;
import model.modelstates.*;
import model.playerdata.Player;
import model.weaponscard.Weapon;

import java.io.*;

import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Thread.sleep;

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
            FinalFrenzyState finalFrenzyState = new FinalFrenzyState(allPlay,stateHashMap, idClientList);
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
            stateHashMap.put(StatesEnum.FRENZY, finalFrenzyState);

            String game = (String) objectInputStream.readObject();
            if(game.equals("continue")){

                String nickname = (String) objectInputStream.readObject();
                if(idClientList.getNicknameList().contains(nickname)) {
                    objectOutputStream.writeObject(true);
                    while (true) {
                        if (allPlay.isStarting()) {
                            break;
                        }
                        if (!(idClientList.getNicknameList().contains(nickname))) {
                            idClientList.getPlayerArrayList().add(idClientList.getNickPlayer().get(nickname));
                        }
                    }

                    Integer token = idClientList.addClient();
                    objectOutputStream.writeObject(token);

                    this.player = idClientList.getNickPlayer().get(nickname);
                    objectOutputStream.writeObject(player);

                    if (!(idClientList.getPlayerArrayList().contains(player))) {
                        idClientList.getPlayerArrayList().add(idClientList.getNickPlayer().get(nickname));
                    }

                    Updater updater = new Updater(allPlay);
                    UpdatePacket updatePacket = updater.updateClient(player);
                    objectOutputStream.writeObject(updatePacket);


                    allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap);


                    StartGame startGame = new StartGame(allPlay, player, objectInputStream, objectOutputStream, stateHashMap, updater, idClientList);
                    startGame.start();

                    while (true) {
                        if (allPlay.isEndgame() == true) {
                            socket.close();
                            break;
                        }
                    }
                }
                else{
                    objectOutputStream.writeObject(false);
                }

            }
            else if(game.equals("new game")) {
                String nickname;
                while(true){
                    nickname = (String) objectInputStream.readObject();
                    if(idClientList.getNicknameList().contains(nickname)){
                        objectOutputStream.writeObject(false);
                    }
                    else{
                        idClientList.getNicknameList().add(nickname);
                        objectOutputStream.writeObject(true);
                        break;
                    }
                }

                Mode mode= (Mode) objectInputStream.readObject();
                if(mode.equals(Mode.BASE)){
                    allPlay.getVoteMode().setVoteResult(0);
                }
                else if(mode.equals(Mode.DOMINATION)){
                    allPlay.getVoteMode().setVoteResult(1);
                }


                allPlay.addPlayerCounter();
                this.player = serverManagerFunction.chooseCharacterManager(objectInputStream, this.allPlay, objectOutputStream, idClientList);

                idClientList.getNickPlayer().put(nickname, player);
                allPlay.getVoteMap().addPlayerCounter();
                serverManagerFunction.manageVoteMap(allPlay, outMessage, objectInputStream, objectOutputStream);

                allPlay.resetPlayerCounterTemp();


                allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap);
                if (idClientList.getPlayerArrayList().get(0).equals(player)) {
                    allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.SPAWN));
                }

                if(idClientList.getPlayerArrayList().size() < 3) {
                    objectOutputStream.writeObject(false);
                }
                else{
                    objectOutputStream.writeObject(true);
                }


                while(true){
                    if(idClientList.getPlayerArrayList().size() >= 3){
                        allPlay.setWait(false);
                        break;
                    }
                }

                Integer token = idClientList.addClient();
                objectOutputStream.writeObject(token);

                objectOutputStream.writeObject(true);

                while(true) {
                    if(idClientList.getPlayerArrayList().get(0)==this.player){
                        while (true){
                            allPlay.setWait(true);
                            sleep(10 * 1000);
                            objectOutputStream.writeObject(true);
                            sleep(2 * 1000);
                            if (idClientList.getPlayerArrayList().size() >= 3){
                                objectOutputStream.writeObject(true);
                                allPlay.setStarting(true);
                                break;
                            } else {
                                objectOutputStream.writeObject(false);
                                while (true) {
                                    if (idClientList.getPlayerArrayList().size() >= 3) {
                                        objectOutputStream.writeObject(true);
                                        break;
                                    }
                                }
                            }
                        }
                        if(allPlay.isStarting()){
                            break;
                        }
                    }
                    else{
                        if(allPlay.isStarting()){
                            objectOutputStream.writeObject(true);
                            objectOutputStream.writeObject(true);
                            break;
                        }
                    }
                }

                if(idClientList.getPlayerArrayList().get(0)==player) {
                    if (!allPlay.getVoteMap().getInitMap()) {
                        allPlay.getVoteMap().setInitmap();
                        allPlay.getVoteMap().setFinalresult();
                        allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                        allPlay.getStateSelectedMap().setSelectedmap();
                        serverManagerFunction.refillMap(allPlay);
                        allPlay.getVoteMode().setFinalResult();
                        allPlay.getStateSelectedMode().setSelectedmode(allPlay.getVoteMode().getFinalResult());
                    }
                }
                else{
                    sleep(200);
                }


                Updater updater = new Updater(allPlay);
                UpdatePacket updatePacket = updater.updateClient(player);
                objectOutputStream.writeObject(updatePacket);

                StartGame startGame = new StartGame(allPlay, player, objectInputStream, objectOutputStream, stateHashMap, updater, idClientList);
                startGame.start();


                while(true) {
                    if (allPlay.isEndgame() == true) {
                        socket.close();
                        break;
                    }
                }
            }
        }
        catch (IOException e) {
            try {
                idClientList.getPlayerArrayList().remove(player);
                idClientList.getClientlist().remove(allPlay.getCurrentPlayerState().get(player).getToken());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        } catch (CloneNotSupportedException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
