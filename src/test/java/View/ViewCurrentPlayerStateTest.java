package View;

import Model.Action;
import Model.PlayerBoard;
import Model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewCurrentPlayerStateTest {

    @Test
    void isViewTurn() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.isViewTurn(), false);
    }

    @Test
    void setViewTurn() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.setViewTurn(true);
        assertEquals(p.isViewTurn(), true);
    }

    @Test
    void getActionState() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getActionState(), null);
    }

    @Test
    void setActionState() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.setActionState(Action.SHOOT);
        assertEquals(p.getActionState(), Action.SHOOT);
    }

    @Test
    void getPlayerPosition() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getPlayerPosition(), null);
    }

    @Test
    void setPlayerPosition() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        Position pos = new Position();
        p.setPlayerPosition(pos);
        assertEquals(p.getPlayerPosition(), pos);
    }

    @Test
    void getActionCunter() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getActionCunter(), 2);
    }

    @Test
    void resetActionCunter() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.decreaseActionCounter();
        p.resetActionCunter();
        assertEquals(p.getActionCunter(), 2);
    }

    @Test
    void decreaseActionCounter() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.decreaseActionCounter();
        assertEquals(p.getActionCunter(), 1);
        p.decreaseActionCounter();
        assertEquals(p.getActionCunter(), 0);
    }

    @Test
    void getBoard() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getBoard(), null);
    }

    @Test
    void setBoard() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        PlayerBoard b = new PlayerBoard();
        p.setBoard(b);
        assertEquals(p.getBoard(), b);
    }
}