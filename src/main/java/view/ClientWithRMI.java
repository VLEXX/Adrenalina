/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.*;
import model.playerdata.Player;
import servercontroller.ServerManagerFunctionInterfaceRMI;
import servercontroller.StateBoxInterface;
import servercontroller.UpdaterInterface;
import view.viewstates.*;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ClientWithRMI implements ClientStrategy {

    public void startClient() throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8080);

        ClientManagerRMI clientManager = new ClientManagerRMI();
        ViewDatabase viewDatabase = new ViewDatabase();
        ViewUpdater viewUpdater = new ViewUpdater();

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
        HashMap<StatesEnum, ViewState> viewStateHashMap = new HashMap<>();
        viewStateHashMap.put(StatesEnum.ACTION, actionState);
        viewStateHashMap.put(StatesEnum.END, endTurnState);
        viewStateHashMap.put(StatesEnum.MOVE, moveState);
        viewStateHashMap.put(StatesEnum.WAIT, waitingState);
        viewStateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        viewStateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        viewStateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        viewStateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        viewStateHashMap.put(StatesEnum.POWERUP, powerupState);
        viewStateHashMap.put(StatesEnum.SPAWN, spawnState);
        viewStateHashMap.put(StatesEnum.FRENZY, frenzyState);

        Scanner stdin = new Scanner(System.in);


        IDClientListInterface idClientList = (IDClientListInterface) registry.lookup("IDClientList");
        InitializeAllPlayInterface allPlay = (InitializeAllPlayInterface) registry.lookup("allPlay");
        UpdaterInterface updater = (UpdaterInterface) registry.lookup("Updater");
        ServerManagerFunctionInterfaceRMI serverManagerFunctionRMI = (ServerManagerFunctionInterfaceRMI) registry.lookup("ServerManagerFunctionRMI");
        VoteMapInterface voteMap = (VoteMapInterface) registry.lookup("VoteMap");
        StateBoxInterface stateHashMap = (StateBoxInterface) registry.lookup("StateBox");
        ManageEndTurnInterface manageEndTurn = (ManageEndTurnInterface) registry.lookup("ManageEndTurn");

        String game = clientManager.manageStart(stdin);
        if(game.equals("continue")){

            while (true) {
                String nickname = clientManager.manageNickname(stdin);
                if (serverManagerFunctionRMI.isInArrayNick(nickname)) {
                    while (true) {
                        if (allPlay.isStarting()) {
                            break;
                        }
                        if (!(idClientList.isInPlayerList(nickname))) {
                            idClientList.addPlayerInList(nickname);
                            System.out.println("Nickname OK!\n\nFew seconds to start...\n");
                        }
                    }

                    if(!(idClientList.isInPlayerList(nickname))){
                        idClientList.addPlayerInList(nickname);
                    }

                    int token = idClientList.addClient();
                    viewDatabase.setClientToken(token);


                    Player player = idClientList.getPlayerFromNick(nickname);
                    idClientList.putNickPlayer(player, token);

                    viewDatabase.setThisplayer(player);

                    UpdatePacket updatePacket = null;
                    try {
                        updatePacket = updater.updateClient(player);
                    } catch (CloneNotSupportedException e) {}

                    allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap.getHashMap());
                    viewDatabase.getViewState().put(player, viewStateHashMap.get(StatesEnum.WAIT));

                    viewUpdater.updateView(updatePacket, viewDatabase, viewStateHashMap, player);

                    ViewStartGameRMI viewStartGameRMI = new ViewStartGameRMI(player, stdin, viewDatabase, viewStateHashMap, updater, allPlay, manageEndTurn, stateHashMap, idClientList);
                    viewStartGameRMI.start();

                    break;

                }
                else{
                    System.out.println("Nickname not found! Please insert a valide nickname...\n");
                }
            }

        }
        else if(game.equals("new game")) {
            Player player = null;
            int token = idClientList.addClient();
            if (token == -1) {
                System.out.println("\n" + "\u001B[31m" + "Because the Server is dark and full of connections." + "\u001B[0m");
            } else {

                String nickname = clientManager.manageNickname(stdin);
                serverManagerFunctionRMI.manageNickname(nickname);


                System.out.println("\n");
                allPlay.addPlayerCounter();
                viewDatabase.setClientToken(token);

                CurrentDeckState currentDeckState = allPlay.getCurrentDeckState();
                player = clientManager.manageChoice(stdin, currentDeckState);
                MessageEnum messageEnumOK;
                while (true) {
                    messageEnumOK = serverManagerFunctionRMI.chooseCharacterManager(player);
                    if (messageEnumOK.equals(MessageEnum.OK)) {
                        System.out.println("\nYou choose: " + player + "\n");
                        break;
                    }
                }

                idClientList.putNickPlayer(player, token);


                serverManagerFunctionRMI.manageNickPlayer(nickname, player);
                voteMap.addPlayerCounter();
                System.out.println("\n");
                int mapnumber = clientManager.manageVote(stdin);
                while (true) {
                    messageEnumOK = serverManagerFunctionRMI.manageVoteMap(mapnumber);
                    if (messageEnumOK.equals(MessageEnum.OK)) {
                        break;
                    }
                }

                allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap.getHashMap());
                viewDatabase.getViewState().put(player, viewStateHashMap.get(StatesEnum.WAIT));

                if (idClientList.getPlayerArrayList().get(0).equals(player)) {
                    allPlay.replaceInHashMap(player, StatesEnum.SPAWN, stateHashMap.getHashMap());
                    viewDatabase.getViewState().replace(player, viewStateHashMap.get(StatesEnum.SPAWN));
                }

                if(idClientList.getPlayerArrayList().size() < 3){
                    System.out.println("Waiting for other players...\n");
                }

                idClientList.addConnection(player);
                idClientList.addPlayerRMI(player);
                CheckConnectionRMI checkConnectionRMI = new CheckConnectionRMI(idClientList, allPlay, player);
                checkConnectionRMI.start();

                while(true){
                    if(idClientList.getPlayerArrayList().size() >= 3){
                        break;
                    }
                }

                System.out.println(token);
                System.out.println("Few seconds to start...\n");

                while (true) {
                    try {
                        if(idClientList.getPlayerArrayList().get(0)!=player){
                            sleep(200);
                        }
                        if (!allPlay.isWait()) {
                            allPlay.setWait(true);
                            while(true) {
                                idClientList.setSleepPlayer(player);
                                sleep(10 * 1000);
                                if (idClientList.getPlayerArrayList().size() >= 3) {
                                    allPlay.setStarting(true);
                                    idClientList.setSleepPlayer(null);
                                    break;
                                } else {
                                    System.out.println("Waiting for other players...\n");
                                    while (true) {
                                        if (idClientList.getPlayerArrayList().size() >= 3) {
                                            break;
                                        }
                                    }
                                    System.out.println("New player is in! Few seconds to start...\n");
                                }
                            }
                        } else {
                            if (allPlay.isStarting()) {
                                break;
                            }
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                UpdatePacket updatePacket = null;
                try {
                    updatePacket = updater.updateClient(player);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                viewUpdater.updateView(updatePacket, viewDatabase, viewStateHashMap, player);

                ViewStartGameRMI viewStartGameRMI = new ViewStartGameRMI(player, stdin, viewDatabase, viewStateHashMap, updater, allPlay, manageEndTurn, stateHashMap, idClientList);
                viewStartGameRMI.start();

            }


        }
    }
}
