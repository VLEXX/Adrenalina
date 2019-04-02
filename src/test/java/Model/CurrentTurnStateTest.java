//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrentTurnStateTest {

    @Test
    void getPlayeTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerTurn(Player.Yellow);
        assertEquals(Player.Yellow,c.getPlayerTurn());
    }

    @Test
    void setPlayerTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerTurn(Player.Blue);
        assertEquals(Player.Blue,c.getPlayerTurn());
    }
}