/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.*;
import model.modelstates.*;
import model.playerdata.Player;
import servercontroller.ServerManagerFunctionInterfaceRMI;
import servercontroller.StateBoxInterface;
import servercontroller.UpdaterInterface;
import view.viewstates.*;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class ClientWithRMI implements ClientStrategy {

    public void startClient() throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);

        String[] e = registry.list();

        String remoteObject = "server_central";

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

        Scanner stdin = new Scanner(System.in);

        IDClientListInterface idClientList = (IDClientListInterface) registry.lookup("IDClientList");
        InitializeAllPlayInterface allPlay = (InitializeAllPlayInterface) registry.lookup("Playercountertemp");
        UpdaterInterface updater = (UpdaterInterface) registry.lookup("Updater");
        ServerManagerFunctionInterfaceRMI serverManagerFunctionRMI = (ServerManagerFunctionInterfaceRMI) registry.lookup("ServerManagerFunctionRMI");
        VoteMapInterface voteMap = (VoteMapInterface) registry.lookup("VoteMap");
        StateBoxInterface stateHashMap = (StateBoxInterface) registry.lookup("StateBox");

        int token = idClientList.addClient();
        if(token==-1){
            System.out.println("\n" + "\u001B[31m" + "Because the Server is dark and full of connections." + "\u001B[0m");
        }
        else{
            System.out.println("\n");
            allPlay.addPlayerCounter();


            int playercounter = allPlay.getPlayercountertemp();
            CurrentDeckState currentDeckState = allPlay.getCurrentDeckState();
            Player player = clientManager.manageChoice(stdin, currentDeckState);
            MessageEnum messageEnumOK;
            while(true){
                messageEnumOK=serverManagerFunctionRMI.chooseCharacterManager(player);
                if(messageEnumOK.equals(MessageEnum.OK)){
                    System.out.println("\nYou choose: " + player + "\n");
                    break;
                }
            }
            System.out.println("Waiting for the opponent...\n");
            while (true){
                if(allPlay.getPlayercountertemp()==0){
                    break;
                }
            }

            voteMap.addPlayerCounter();
            System.out.println("\n");
            int mapnumber = clientManager.manageVote(stdin);
            while(true){
                messageEnumOK=serverManagerFunctionRMI.manageVoteMap(mapnumber);
                if(messageEnumOK.equals(MessageEnum.OK)){
                    break;
                }
            }
            System.out.println("Waiting for the opponent...\n");
            while (true){
                if(voteMap.getPlayerCounter()==0){
                    break;
                }
            }
            int map=voteMap.getFinalresult()+1;
            System.out.println("Map Selected: " + map + "\n");


            allPlay.putInHashMapState(player,StatesEnum.WAIT, stateHashMap.getHashMap());
            if(idClientList.getPlayerArrayList().get(0).equals(player)){
                allPlay.getHashMapState().replace(player, stateHashMap.getHashMap().get(StatesEnum.SPAWN));
            }

            UpdatePacket updatePacket = updater.updateClient(player);
            viewUpdater.updateView(updatePacket, viewDatabase, viewStateHashMap, player);


        }
    }
}
