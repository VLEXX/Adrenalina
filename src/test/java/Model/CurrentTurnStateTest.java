//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrentTurnStateTest {

    @Test
    void getPlayeTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerturn(Player.YELLOW);
        assertEquals(Player.YELLOW,c.getPlayerturn());
    }

    @Test
    void setPlayerTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerturn(Player.BLUE);
        assertEquals(Player.BLUE,c.getPlayerturn());
    }
}