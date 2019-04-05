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
        assertEquals(p.isViewturn(), false);
    }

    @Test
    void setViewTurn() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.setViewturn(true);
        assertEquals(p.isViewturn(), true);
    }

    @Test
    void getActionState() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getActionstate(), null);
    }

    @Test
    void setActionState() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.setActionstate(Action.SHOOT);
        assertEquals(p.getActionstate(), Action.SHOOT);
    }

    @Test
    void getPlayerPosition() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getPlayerposition(), null);
    }

    @Test
    void setPlayerPosition() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        Position pos = new Position();
        p.setPlayerposition(pos);
        assertEquals(p.getPlayerposition(), pos);
    }

    @Test
    void getActionCunter() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        assertEquals(p.getActioncounter(), 2);
    }

    @Test
    void resetActionCunter() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.decreaseActionCounter();
        p.resetActionCunter();
        assertEquals(p.getActioncounter(), 2);
    }

    @Test
    void decreaseActionCounter() {
        ViewCurrentPlayerState p = new ViewCurrentPlayerState();
        p.decreaseActionCounter();
        assertEquals(p.getActioncounter(), 1);
        p.decreaseActionCounter();
        assertEquals(p.getActioncounter(), 0);
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