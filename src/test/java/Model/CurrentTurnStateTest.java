//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrentTurnStateTest {

    @Test
    void getPlayerTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerturn(Player.YELLOW);
        assertEquals(Player.YELLOW, c.getPlayerturn());
    }

    @Test
    void setPlayerTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerturn(Player.BLUE);
        assertEquals(Player.BLUE, c.getPlayerturn());
    }

    @Test
    void notifyObserver() {
        CurrentTurnState c = new CurrentTurnState();
        CurrentPlayerState s = new CurrentPlayerState(Player.YELLOW);
        s.setActiveplayer(Player.YELLOW);
        c.addObserver(s);
        c.setPlayerturn(Player.YELLOW);
        c.notifyObserver();
        assertEquals(s.isActiveturn(), true);
    }

    @Test
    void removeObserver() {
        CurrentTurnState c = new CurrentTurnState();
        CurrentPlayerState s = new CurrentPlayerState(Player.YELLOW);
        s.setActiveplayer(Player.YELLOW);
        c.setPlayerturn(Player.YELLOW);
        c.addObserver(s);
        assertEquals(c.getObservers().isEmpty(), false);
        c.removeObserver(s);
        assertEquals(c.getObservers().isEmpty(), true);
    }

    @Test
    void addObserver() {
        CurrentTurnState c = new CurrentTurnState();
        CurrentPlayerState s = new CurrentPlayerState(Player.YELLOW);
        s.setActiveplayer(Player.YELLOW);
        c.setPlayerturn(Player.YELLOW);
        c.addObserver(s);
        assertEquals(c.getObservers().isEmpty(), false);
    }

    @Test
    void getObservers() {
        CurrentTurnState c = new CurrentTurnState();
        assertEquals(c.getObservers().isEmpty(), true);
    }
}