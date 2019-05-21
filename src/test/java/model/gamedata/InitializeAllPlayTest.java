/**
 * @author Giulia Rivara
 */
package model.gamedata;

import model.datapacket.StatesEnum;
import model.modelstates.*;
import servercontroller.UpdateThread;
import model.gamedata.InitializeAllPlay;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializeAllPlayTest {

    @Test
    void getChartScore() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getChartScore().getScore()[0], 0);
    }

    @Test
    void getVoteMap() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getVoteMap().getFinalresult(), -1);
    }

    @Test
    void getCurrentPlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentPlayerState().size(), 0);
    }

    @Test
    void getCurrentTurnState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentTurnState().size(), 0);
    }

    @Test
    void getStateSelectedMap() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getStateSelectedMap().getSelectedmap(), null);
    }

    @Test
    void getStateSelectedMode() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getStateSelectedMode().getSelectedmode(), null);
    }

    @Test
    void getVoteMode() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getVoteMode().getFinalResult(), -1);
    }

    @Test
    void getIdClientList() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getIdClientList().getPlayerArrayList().isEmpty(), true);
    }

    @Test
    void getCurrentDeckState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentDeckState().getPlayers().size(), 5);
    }

    @Test
    void addPlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        State state = new MoveState(initializeAllPlay, hashMap);
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void updatePlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        State state = new MoveState(initializeAllPlay, hashMap);
        State state1 = new ShootFirstState(initializeAllPlay, hashMap);
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        initializeAllPlay.updatePlayerState(Player.YELLOW, state1);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state1);
    }

    @Test
    void getPlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        State state = new MoveState(initializeAllPlay, hashMap);
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void addPlayerCounter() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.addPlayerCounter();
        assertEquals(initializeAllPlay.getPlayercounter(), 1);
        assertEquals(initializeAllPlay.getPlayercountertemp(), 1);
    }

    @Test
    void decreasePlayerCounterTemp() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.addPlayerCounter();
        initializeAllPlay.addPlayerCounter();
        initializeAllPlay.decreasePlayerCounterTemp();
        assertEquals(initializeAllPlay.getPlayercountertemp(), 1);
        initializeAllPlay.resetPlayerCounterTemp();
        assertEquals(initializeAllPlay.getPlayercountertemp(), 2);
    }

    @Test
    void getHashMapState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.getHashMapState().put(Player.BLUE, null);
        assertEquals(initializeAllPlay.getHashMapState().containsKey(Player.BLUE), true);
    }


    @Test
    void putInHashMapState() {
        InitializeAllPlay allPlay = new InitializeAllPlay();

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap);
        MoveState moveState = new MoveState(allPlay, stateHashMap);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap);
        ShootFirstState shootFirstState2 = new ShootFirstState(allPlay, stateHashMap);
        ShootSecondState shootSecondState2 = new ShootSecondState(allPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap);
        PowerupState powerupState1 = new PowerupState(allPlay, stateHashMap);
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
    void setEndgame() {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        allPlay.setEndgame(true);
        assertEquals(allPlay.isEndgame(), true);
    }

    @Test
    void setTempState() {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        State state = new ShootFirstState(allPlay, null);
        allPlay.setTempState(state);
        assertEquals(allPlay.getTempState(), state);
    }
}