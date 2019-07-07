/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
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
}