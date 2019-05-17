package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MoveStateTest {

    @Test
    void cellFinder() {
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
        MoveState ms = new MoveState(i, hashMap);
        assertEquals(ms.cellFinder(i, 1, Player.BLUE).getCellId(), i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).getCellId());
        assertEquals(ms.cellFinder(i, 22, Player.BLUE), null);
        assertEquals(ms.cellFinder(i, 7, Player.BLUE), null);

    }

    @Test
    void setMove() {
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
        MoveState ms = new MoveState(i, hashMap);
        assertEquals(ms.setMove(i, i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1), Player.BLUE), 0);
        i.getCurrentPlayerState().get(Player.BLUE).setActiveplayer(Player.YELLOW);
        assertEquals(ms.setMove(i, i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1), Player.BLUE), -1);

    }

    @Test
    void cellIsReachable() {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState ps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        ps.setPlayerposition(p);
        i.getCurrentPlayerState().put(Player.BLUE, ps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        MoveState ms = new MoveState(i, hashMap);
        assertTrue(ms.cellIsReachable(i, Player.BLUE, i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0)));
        assertFalse(ms.cellIsReachable(i, Player.BLUE, i.getStateSelectedMap().getSelectedmap().getRoomList().get(4).getCellsList().get(0)));

    }

    @Test
    void doAction() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        cps.setPlayerposition(p);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, cps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        MoveState ms = new MoveState(initializeAllPlay, hashMap);

        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(Player.BLUE);
        dataPacket.setCell(initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));

        assertEquals(moveState.doAction(dataPacket), MessageEnum.OK);
        assertEquals(moveState.doAction(dataPacket), MessageEnum.OK);
        initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).setActiveplayer(Player.YELLOW);

        assertEquals(moveState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);    }
}