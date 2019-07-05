/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.*;
import model.modelstates.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

/**
 * Thread that identify ServerRMI and manage client RMI method call
 */
public class ServerRMI extends Thread {
    private InitializeAllPlay allPlay;
    private IDClientList idClientList;

    public ServerRMI(InitializeAllPlay allPlay, IDClientList clientList){
        this.allPlay = allPlay;
        this.idClientList=clientList;
    }

    public void startServerRMI() throws RemoteException, AlreadyBoundException {

        Updater updater = new Updater(this.allPlay);
        ServerManagerFunctionRMI serverManagerFunctionRMI = new ServerManagerFunctionRMI(this.allPlay, this.idClientList);
        StateBox stateBox = new StateBox(this.allPlay, idClientList);
        ManageEndTurn manageEndTurn = new ManageEndTurn(allPlay, idClientList);


        System.out.println("RMI Server ready...");
        Registry registry = LocateRegistry.createRegistry(8090);


        registry.bind("allPlay", this.allPlay);
        registry.bind("IDClientList", this.idClientList);
        registry.bind("Updater", updater);
        registry.bind("ServerManagerFunctionRMI", serverManagerFunctionRMI);
        registry.bind("VoteMap", allPlay.getVoteMap());
        registry.bind("StateBox", stateBox);
        registry.bind("ManageEndTurn", manageEndTurn);
        registry.bind("StateSelectedMap", this.allPlay.getStateSelectedMap());
        registry.bind("VoteMode", this.allPlay.getVoteMode());
        registry.bind("StateSelectedMode", this.allPlay.getStateSelectedMode());

        HashMap<StatesEnum, model.modelstates.State> stateHashMap = new HashMap<>();
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

        CheckConnectionController checkConnectionController = new CheckConnectionController(idClientList,allPlay,stateHashMap);

        checkConnectionController.start();
    }

    public void run(){
        try {
            startServerRMI();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
