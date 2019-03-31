package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrentTurnStateTest {

    @Test
    void CurrentTurnState(){
        CurrentTurnState c = new CurrentTurnState();
        assertNotNull(c);
    }
    @Test
    void getPlayeTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerTurn(Player.Yellow);
        assertEquals(Player.Yellow,c.getPlayeTurn());
    }

    @Test
    void setPlayerTurn() {
        CurrentTurnState c = new CurrentTurnState();
        c.setPlayerTurn(Player.Blue);
        assertEquals(Player.Blue,c.getPlayeTurn());
    }
}