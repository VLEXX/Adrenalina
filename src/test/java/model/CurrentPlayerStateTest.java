//Author: Federico Scatà
package model;

import model.gamedata.CurrentTurnState;
import model.map.Position;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrentPlayerStateTest {

    @Test
    void getPlayerPosition() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        assertEquals(p.getPlayerposition(), null);
    }

    @Test
    void setPlayerPosition() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        Position pos = new Position();
        p.setPlayerposition(pos);
        assertEquals(p.getPlayerposition(), pos);
    }

    @Test
    void isActiveTurn() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        assertEquals(p.isActiveturn(), false);
    }

    @Test
    void setActiveTurn() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        p.setActiveturn(true);
        assertEquals(p.isActiveturn(), true);
    }

    @Test
    void getBoard() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        assertEquals(p.getBoard(), null);
    }

    @Test
    void setBoard() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        PlayerBoard b = new PlayerBoard();
        p.setBoard(b);
        assertEquals(p.getBoard(), b);
    }

    @Test
    void getActionCounter() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        assertEquals(p.getActioncounter(), 2);
    }

    @Test
    void resetActionCounter() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        p.decreaseActionCounter();
        p.resetActionCounter();
        assertEquals(p.getActioncounter(), 2);
    }

    @Test
    void decreaseActionCounter() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        p.decreaseActionCounter();
        assertEquals(p.getActioncounter(), 1);
    }

    @Test
    void getActivePlayer() {
        CurrentPlayerState p = new CurrentPlayerState(Player.YELLOW);
        assertEquals(p.getActiveplayer(), Player.YELLOW);
    }

    @Test
    void setActivePlayer() {
        CurrentPlayerState p = new CurrentPlayerState(Player.BLACK);
        p.setActiveplayer(Player.BLACK);
        assertEquals(p.getActiveplayer(), Player.BLACK);
    }

    @Test
    void update() {
        CurrentTurnState c = new CurrentTurnState();
        CurrentPlayerState s = new CurrentPlayerState(Player.YELLOW);
        s.setActiveplayer(Player.YELLOW);
        c.addObserver(s);
        c.setPlayerturn(Player.YELLOW);
        c.notifyObserver();
        assertEquals(s.isActiveturn(), true);
        s.setActiveturn(false);
        c.setPlayerturn(Player.BLACK);
        c.notifyObserver();
        assertEquals(s.isActiveturn(), false);
    }

    @Test
    void getControlMarks(){
        CurrentPlayerState c = new CurrentPlayerState(Player.BLACK);
        c.addControlMarks(Player.YELLOW, 3);
        assertEquals(c.getControlMarks().get(Player.YELLOW), 3);
    }
}