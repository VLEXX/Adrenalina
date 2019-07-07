/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.StatesEnum;
import model.gamedata.Mode;
import model.playerdata.Player;
import view.viewstates.*;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Design Pattern Strategy to manage ClientWithRMI
 */
public class ClientWithSocket implements ClientStrategy{
        private String ip;
        private int port;


        public ClientWithSocket(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public void startClient() throws IOException, ClassNotFoundException {
            try {
                Socket socket = new Socket(ip, port);

                ClientManager clientManager = new ClientManager();

                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                Scanner stdin = new Scanner(System.in);

                ViewActionState actionState = new ViewActionState();
                ViewEndturnState endTurnState = new ViewEndturnState();
                ViewMoveState moveState = new ViewMoveState();
                ViewWaitingState waitingState = new ViewWaitingState();
                ViewShootFirstState shootFirstState = new ViewShootFirstState();
                ViewShootSecondState shootSecondState = new ViewShootSecondState();
                ViewShootThirdState shootThirdState = new ViewShootThirdState();
                ViewPickupState pickUpState = new ViewPickupState();
                ViewPowerupState powerupState = new ViewPowerupState();
                ViewSpawnState spawnState = new ViewSpawnState();
                ViewFrenzyState frenzyState = new ViewFrenzyState();
                HashMap<StatesEnum, ViewState> stateHashMap = new HashMap<>();
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
                stateHashMap.put(StatesEnum.FRENZY, frenzyState);

                String game = clientManager.manageStart(stdin);
                if(game.equals("continue")){

                    ViewDatabase viewDatabase = new ViewDatabase();

                    objectOutputStream.writeObject(game);

                    String nickname;
                    while(true) {
                        nickname = clientManager.manageNickname(stdin);
                        viewDatabase.setNickname(nickname);
                        objectOutputStream.writeObject(nickname);
                        boolean go;
                        go = (boolean) objectInputStream.readObject();
                        if (go) {
                            break;
                        } else {
                            System.out.println("Nickname not found! Please insert a valide nickname...\n");
                        }
                    }
                        int token = (Integer) objectInputStream.readObject();
                        viewDatabase.setClientToken(token);

                        Player player = (Player) objectInputStream.readObject();
                        viewDatabase.setThisplayer(player);


                        ViewStartGame startGame = new ViewStartGame(viewDatabase.getThisplayer(), objectInputStream, objectOutputStream, stdin, viewDatabase, stateHashMap);
                        startGame.start();



                }
                else if(game.equals("new game")){

                    ViewDatabase viewDatabase = new ViewDatabase();

                    objectOutputStream.writeObject(game);
                    String nickname;

                    while(true){
                        nickname = clientManager.manageNickname(stdin);
                        objectOutputStream.writeObject(nickname);
                        boolean go = (boolean) objectInputStream.readObject();
                        if(go){
                            break;
                        }
                        else{
                            System.out.println("Nickname not available!\nChoose an other one...\n");
                        }
                    }
                    viewDatabase.setNickname(nickname);

                    System.out.println("\n");

                    Mode mode = clientManager.manageMode(stdin);
                    objectOutputStream.writeObject(mode);

                    System.out.println("\n");

                    clientManager.manageChoice(stdin, objectOutputStream, objectInputStream, viewDatabase);
                    clientManager.manageVote(stdin, objectOutputStream);

                    boolean go;
                    go = (boolean) objectInputStream.readObject();

                    if(!go){
                        System.out.println("Waiting for other players...\n");
                    }


                    int token = (Integer) objectInputStream.readObject();
                    viewDatabase.setClientToken(token);



                    go = (boolean) objectInputStream.readObject();
                    System.out.println("\nFew seconds to start...\n");

                    while(true) {
                        while(true){
                            go = (boolean) objectInputStream.readObject();
                            if(go){
                                break;
                            }
                        }
                        go = (boolean) objectInputStream.readObject();
                        go = (boolean) objectInputStream.readObject();
                        if(go){
                            break;
                        }
                    }

                    System.out.println("\n");

                    if(go){
                        ViewStartGame startGame = new ViewStartGame(viewDatabase.getThisplayer(), objectInputStream, objectOutputStream, stdin, viewDatabase, stateHashMap);
                        startGame.start();
                    }
                }
            }
            catch (ConnectException e){
                System.out.println("\n" + "\u001B[31m" + "Because the Server is dark and full of connections." + "\u001B[0m");
            }
        }
}
