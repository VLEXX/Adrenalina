package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.modelstates.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class StateBox extends UnicastRemoteObject implements Remote, StateBoxInterface {

    private HashMap<StatesEnum, State> hashMap;


    public StateBox(InitializeAllPlay allPlay, IDClientList clientList) throws RemoteException {

        this.hashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, hashMap,clientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, hashMap, clientList);
        MoveState moveState = new MoveState(allPlay, hashMap, clientList);
        WaitingState waitingState = new WaitingState(allPlay, hashMap, clientList);
        ShootFirstState shootFirstState = new ShootFirstState(allPlay, hashMap, clientList);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, hashMap, clientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, hashMap, clientList);
        PickUpState pickUpState = new PickUpState(allPlay, hashMap, clientList);
        PowerupState powerupState = new PowerupState(allPlay, hashMap, clientList);
        SpawnState spawnState = new SpawnState(allPlay, hashMap, clientList);
        hashMap.put(StatesEnum.ACTION, actionState);
        hashMap.put(StatesEnum.END, endTurnState);
        hashMap.put(StatesEnum.MOVE, moveState);
        hashMap.put(StatesEnum.WAIT, waitingState);
        hashMap.put(StatesEnum.SHOOT, shootFirstState);
        hashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        hashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        hashMap.put(StatesEnum.PICK_UP, pickUpState);
        hashMap.put(StatesEnum.POWERUP, powerupState);
        hashMap.put(StatesEnum.SPAWN, spawnState);
    }

    public synchronized HashMap<StatesEnum, State> getHashMap() throws RemoteException {
        return this.hashMap;
    }
}
