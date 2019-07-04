/**
 * @author Giulia Rivara
 */
package model.gamedata;

import model.datapacket.StatesEnum;
import model.modelstates.*;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializeAllPlayTest {

    @Test
    void getChartScore() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getChartScore().getScore()[0], 0);
    }

    @Test
    void getVoteMap() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getVoteMap().getFinalresult(), -1);
    }

    @Test
    void getCurrentPlayerState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentPlayerState().size(), 0);
    }

    @Test
    void getCurrentTurnState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentTurnState().size(), 0);
    }

    @Test
    void getStateSelectedMap() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getStateSelectedMap().getSelectedmap(), null);
    }

    @Test
    void getStateSelectedMode() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getStateSelectedMode().getSelectedmode(), null);
    }

    @Test
    void getVoteMode() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getVoteMode().getFinalResult(), null);
    }

    @Test
    void getCurrentDeckState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentDeckState().getPlayers().size(), 5);
    }

    @Test
    void addPlayerState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        State state = new MoveState(initializeAllPlay, hashMap, idClientList);
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void updatePlayerState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        State state = new MoveState(initializeAllPlay, hashMap, idClientList);
        State state1 = new ShootFirstState(initializeAllPlay, hashMap, idClientList);
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        initializeAllPlay.updatePlayerState(Player.YELLOW, state1);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state1);
    }

    @Test
    void getPlayerState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        State state = new MoveState(initializeAllPlay, hashMap, idClientList);
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void addPlayerCounter() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.addPlayerCounter();
        assertEquals(initializeAllPlay.getPlayercounter(), 1);
        assertEquals(initializeAllPlay.getPlayercountertemp(), 1);
    }

    @Test
    void decreasePlayerCounterTemp() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.addPlayerCounter();
        initializeAllPlay.addPlayerCounter();
        initializeAllPlay.decreasePlayerCounterTemp();
        assertEquals(initializeAllPlay.getPlayercountertemp(), 1);
        initializeAllPlay.resetPlayerCounterTemp();
        assertEquals(initializeAllPlay.getPlayercountertemp(), 2);
    }

    @Test
    void getHashMapState() throws RemoteException {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.getHashMapState().put(Player.BLUE, null);
        assertEquals(initializeAllPlay.getHashMapState().containsKey(Player.BLUE), true);
    }

    @Test
    void putInHashMapState() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(allPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(allPlay, stateHashMap,idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(allPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(allPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);
        allPlay.putInHashMapState(Player.YELLOW, StatesEnum.WAIT, stateHashMap);
        assertEquals(allPlay.getHashMapState().get(Player.YELLOW) instanceof WaitingState, true);
    }

    @Test
    void setEndgame() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        allPlay.setEndgame(true);
        assertEquals(allPlay.isEndgame(), true);
    }

    @Test
    void setTempState() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        State state = new ShootFirstState(allPlay, null, idClientList);
        allPlay.setTempState(state);
        assertEquals(allPlay.getTempState(), state);
    }

    @Test
    void replaceInHashMap() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(allPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(allPlay, stateHashMap,idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(allPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(allPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);
        allPlay.putInHashMapState(Player.YELLOW, StatesEnum.WAIT,stateHashMap);
        allPlay.replaceInHashMap(Player.YELLOW, StatesEnum.ACTION, stateHashMap);
        assertEquals(allPlay.getPlayerState(Player.YELLOW).getNamestate(), StatesEnum.ACTION);
    }

    @Test
    void getPlayerStateTempFrenzy() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        assertEquals(allPlay.getPlayerStateTempFrenzy().isEmpty(), true);
    }

    @Test
    void getVoteFrenzy() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        assertEquals(allPlay.getVoteFrenzy().getResult(),  false);
    }

    @Test
    void setStarting() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        allPlay.setStarting(true);
        assertEquals(allPlay.isStarting(), true);
    }

    @Test
    void setWait() throws RemoteException {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        allPlay.setWait(true);
        assertEquals(allPlay.isWait(), true);
    }
}