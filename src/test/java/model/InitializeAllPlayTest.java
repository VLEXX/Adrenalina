/**
 * @author Giulia Rivara
 */
package model;

import ServerController.UpdateThread;
import model.gamedata.InitializeAllPlay;
import model.modelstates.MoveState;
import model.modelstates.ShootFirstState;
import model.modelstates.State;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

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
        assertEquals(initializeAllPlay.getIdClientList().size(), 0);
    }

    @Test
    void getCurrentDeckState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentDeckState().getPlayers().size(), 5);
    }

    @Test
    void addPlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        State state = new MoveState();
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void updatePlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        State state = new MoveState();
        State state1 = new ShootFirstState();
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        initializeAllPlay.updatePlayerState(Player.YELLOW, state1);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state1);
    }

    @Test
    void getPlayerState() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        State state = new MoveState();
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
    void addObserver() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        UpdateThread updateThread = new UpdateThread(initializeAllPlay, Player.BLACK, null);
        initializeAllPlay.addObserver(updateThread);
        assertEquals(initializeAllPlay.getObservers().contains(updateThread), true);
    }

    @Test
    void removeObserver() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        UpdateThread updateThread = new UpdateThread(initializeAllPlay, Player.BLACK, null);
        initializeAllPlay.addObserver(updateThread);
        assertEquals(initializeAllPlay.getObservers().contains(updateThread), true);
        initializeAllPlay.notifyObserver();
        assertEquals(updateThread.isActiveUpdate(), true);
        initializeAllPlay.removeObserver(updateThread);
        assertEquals(initializeAllPlay.getObservers().contains(updateThread), false);
    }
}