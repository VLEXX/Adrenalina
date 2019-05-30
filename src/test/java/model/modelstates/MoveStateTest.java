package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MoveStateTest {

    @Test
    void cellFinder() throws RemoteException {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(1);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        cps.setPlayerposition(p);
        i.getCurrentPlayerState().put(Player.BLUE, cps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        IDClientList idClientList = new IDClientList();
        MoveState ms = new MoveState(i, hashMap, idClientList);
        assertEquals(ms.cellFinder(i, 1, Player.BLUE).getCellId(), i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).getCellId());
        assertEquals(ms.cellFinder(i, 22, Player.BLUE), null);
        assertEquals(ms.cellFinder(i, 7, Player.BLUE), null);

    }

    @Test
    void setMove() throws RemoteException {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        cps.setPlayerposition(p);
        i.getCurrentPlayerState().put(Player.BLUE, cps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        IDClientList idClientList = new IDClientList();
        MoveState ms = new MoveState(i, hashMap, idClientList);
        assertEquals(ms.setMove(i, i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1), Player.BLUE), 0);
        i.getCurrentPlayerState().get(Player.BLUE).setActiveplayer(Player.YELLOW);
        assertEquals(ms.setMove(i, i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1), Player.BLUE), -1);

    }


    @Test
    void doAction() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        cps.setPlayerposition(p);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, cps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        MoveState ms = new MoveState(initializeAllPlay, hashMap, idClientList);

        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPlayer(Player.BLUE);
        dataPacket.setCell(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));

        assertEquals(moveState.doAction(dataPacket), MessageEnum.OK);
        assertEquals(moveState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);

    }

    @Test
    void doAction2() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        cps.setPlayerposition(p);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, cps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        MoveState ms = new MoveState(initializeAllPlay, hashMap, idClientList);

        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPlayer(Player.BLUE);
        dataPacket.setCell(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        initializeAllPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
        assertEquals(moveState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setToken(0);
        assertEquals(moveState.doAction(dataPacket), MessageEnum.TOKEN_ERROR);
    }
}