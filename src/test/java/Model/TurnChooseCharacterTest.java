//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TurnChooseCharacterTest {

    @Test
    void getChoosingTurn() {
        TurnChooseCharacter cc = new TurnChooseCharacter();
        assertEquals(cc.getChoosingTurn(), null);
    }

    @Test
    void setChoosingTurn() {
        TurnChooseCharacter cc = new TurnChooseCharacter();
        cc.setChoosingTurn(Player.Yellow);
        assertEquals(cc.getChoosingTurn(), Player.Yellow);
    }
}