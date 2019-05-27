package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.modelstates.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class StateBox extends UnicastRemoteObject implements Remote, StateBoxInterface {

    private HashMap<StatesEnum, State> hashMap;


    public StateBox(InitializeAllPlay allPlay) throws RemoteException {

        this.hashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, hashMap);
        EndTurnState endTurnState = new EndTurnState(allPlay, hashMap);
        MoveState moveState = new MoveState(allPlay, hashMap);
        WaitingState waitingState = new WaitingState(allPlay, hashMap);
        ShootFirstState shootFirstState = new ShootFirstState(allPlay, hashMap);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, hashMap);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, hashMap);
        PickUpState pickUpState = new PickUpState(allPlay, hashMap);
        PowerupState powerupState = new PowerupState(allPlay, hashMap);
        SpawnState spawnState = new SpawnState(allPlay, hashMap);
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
