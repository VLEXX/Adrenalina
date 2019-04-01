package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrentPlayerStateTest {

    @Test
    void getPlayerPosition() {
        CurrentPlayerState p = new CurrentPlayerState();
        assertEquals(p.getPlayerPosition(), null);
    }

    @Test
    void setPlayerPosition() {
        CurrentPlayerState p = new CurrentPlayerState();
        Position pos = new Position();
        p.setPlayerPosition(pos);
        assertEquals(p.getPlayerPosition(), pos);
    }

    @Test
    void isActiveTurn() {
        CurrentPlayerState p = new CurrentPlayerState();
        assertEquals(p.isActiveTurn(), false);
    }

    @Test
    void setActiveTurn() {
        CurrentPlayerState p = new CurrentPlayerState();
        p.setActiveTurn(true);
        assertEquals(p.isActiveTurn(), true);
    }

    @Test
    void getBoard() {
        CurrentPlayerState p = new CurrentPlayerState();
        assertEquals(p.getBoard(), null);
    }

    @Test
    void setBoard() {
        CurrentPlayerState p = new CurrentPlayerState();
        PlayerBoard b = new PlayerBoard();
        p.setBoard(b);
        assertEquals(p.getBoard(), b);
    }

    @Test
    void getActionState() {
        CurrentPlayerState p = new CurrentPlayerState();
        assertEquals(p.getActionState(), null);
    }

    @Test
    void setActionState() {
        CurrentPlayerState p = new CurrentPlayerState();
        p.setActionState(Action.Move);
        assertEquals(p.getActionState(), Action.Move);
    }

    @Test
    void getActionCounter() {
        CurrentPlayerState p = new CurrentPlayerState();
        assertEquals(p.getActionCounter(), 2);
    }

    @Test
    void resetActionCounter() {
        CurrentPlayerState p = new CurrentPlayerState();
        p.decreaseActionCounter();
        p.resetActionCounter();
        assertEquals(p.getActionCounter(), 2);
    }

    @Test
    void decreaseActionCounter() {
        CurrentPlayerState p = new CurrentPlayerState();
        p.decreaseActionCounter();
        assertEquals(p.getActionCounter(),1);
    }

    @Test
    void getActivePlayer() {
        CurrentPlayerState p = new CurrentPlayerState();
        assertEquals(p.getActivePlayer(), null);
    }

    @Test
    void setActivePlayer() {
        CurrentPlayerState p = new CurrentPlayerState();
        p.setActivePlayer(Player.Black);
        assertEquals(p.getActivePlayer(), Player.Black);
    }
}