package model.gamedata;

import model.datapacket.StatesEnum;
import model.map.Cell;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManageEndTurnTest {

    @Test
    void manageEndTurn() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        idClientList.getPlayerArrayList().add(Player.YELLOW);
        idClientList.getPlayerArrayList().add(Player.BLUE);
        ManageEndTurn manageEndTurn = new ManageEndTurn(allPlay,idClientList);
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState1 = new CurrentPlayerState(Player.BLUE);
        allPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        allPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState1);
        currentPlayerState.setEndturn(true);
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(allPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(allPlay, stateHashMap, idClientList);
        SpawnState spawnState = new SpawnState(allPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
        stateHashMap.put(StatesEnum.SPAWN, spawnState);
        allPlay.getHashMapState().put(Player.BLUE, stateHashMap.get(StatesEnum.WAIT));
        manageEndTurn.manageEndTurn(Player.YELLOW, stateHashMap);
        assertEquals(allPlay.getHashMapState().get(Player.BLUE).getNamestate(), StatesEnum.SPAWN);
    }

    @Test
    void manageEndTurn2() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        idClientList.getPlayerArrayList().add(Player.YELLOW);
        idClientList.getPlayerArrayList().add(Player.BLUE);
        ManageEndTurn manageEndTurn = new ManageEndTurn(allPlay,idClientList);
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState1 = new CurrentPlayerState(Player.BLUE);
        allPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        allPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState1);
        currentPlayerState.setEndturn(true);
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(allPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(allPlay, stateHashMap, idClientList);
        SpawnState spawnState = new SpawnState(allPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
        stateHashMap.put(StatesEnum.SPAWN, spawnState);
        allPlay.getHashMapState().put(Player.BLUE, stateHashMap.get(StatesEnum.WAIT));
        allPlay.getHashMapState().put(Player.YELLOW, stateHashMap.get(StatesEnum.WAIT));
        idClientList.increaseIndexArray();
        Cell cell = new Cell(1);
        allPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
        manageEndTurn.manageEndTurn(Player.YELLOW, stateHashMap);
        assertEquals(allPlay.getHashMapState().get(Player.YELLOW).getNamestate(), StatesEnum.ACTION);
    }

    @Test
    void manageEndTurn3() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        idClientList.getPlayerArrayList().add(Player.YELLOW);
        idClientList.getPlayerArrayList().add(Player.FLAG);
        ManageEndTurn manageEndTurn = new ManageEndTurn(allPlay,idClientList);
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState1 = new CurrentPlayerState(Player.FLAG);
        allPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        allPlay.getCurrentPlayerState().put(Player.FLAG, currentPlayerState1);
        currentPlayerState.setEndturn(true);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(allPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(allPlay, stateHashMap, idClientList);
        SpawnState spawnState = new SpawnState(allPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
        stateHashMap.put(StatesEnum.SPAWN, spawnState);
        allPlay.getHashMapState().put(Player.FLAG, stateHashMap.get(StatesEnum.POWERUP));
        manageEndTurn.manageEndTurn(Player.YELLOW, stateHashMap);

        assertEquals(allPlay.getHashMapState().get(Player.FLAG).getNamestate(), StatesEnum.SPAWN);
    }
}