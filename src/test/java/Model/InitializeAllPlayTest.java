/**
 * @author Giulia Rivara
 */
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializeAllPlayTest {

    @Test
    void getChartScore() {
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getChartScore().size(), 0);
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
    void getCurrentDeckState(){
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        assertEquals(initializeAllPlay.getCurrentDeckState().getPlayers().size(), 5);
    }

    @Test
    void addPlayerState(){
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        State state = new MoveState();
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void updatePlayerState(){
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        State state = new MoveState();
        State state1 = new ShootState();
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        initializeAllPlay.updatePlayerState(Player.YELLOW, state1);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state1);
    }

    @Test
    void getPlayerState(){
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        State state = new MoveState();
        initializeAllPlay.addPlayerState(Player.YELLOW, state);
        assertEquals(initializeAllPlay.getPlayerState(Player.YELLOW), state);
    }

    @Test
    void addPlayerCounter(){
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.addPlayerCounter();
        assertEquals(initializeAllPlay.getPlayercounter(), 1);
        assertEquals(initializeAllPlay.getPlayercountertemp(), 1);
    }

    @Test
    void decreasePlayerCounterTemp(){
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        initializeAllPlay.addPlayerCounter();
        initializeAllPlay.addPlayerCounter();
        initializeAllPlay.decreasePlayerCounterTemp();
        assertEquals(initializeAllPlay.getPlayercountertemp(), 1);
        initializeAllPlay.resetPlayerCounterTemp();
        assertEquals(initializeAllPlay.getPlayercountertemp(), 2);
    }

}