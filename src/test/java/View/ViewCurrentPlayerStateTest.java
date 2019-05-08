package View;

import Model.CurrentPlayerState;
import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewCurrentPlayerStateTest {

    @Test
    void getCurrentPlayerState() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        assertEquals(v.getCurrentPlayerState(), null);
    }

    @Test
    void getActiveplayer() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        assertEquals(v.getActiveplayer(), null);
    }

    @Test
    void setCurrentPlayerState() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        CurrentPlayerState c = new CurrentPlayerState(Player.BLACK);
        c.setActiveplayer(Player.BLACK);
        v.setCurrentPlayerState(c);
        assertEquals(v.getCurrentPlayerState().getActiveplayer(), Player.BLACK);
    }

    @Test
    void setActiveplayer() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        v.setActiveplayer(Player.YELLOW);
        assertEquals(v.getActiveplayer(), Player.YELLOW);
    }
}