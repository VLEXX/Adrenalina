/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.playerdata.MarksBox;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarksBoxTest {

    @Test
    void setMyMarksMap() {
        Player player = Player.YELLOW;
        int marks = 2;
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(player, marks);
        assertEquals(marksBox.getMyMarksMap().size(), 1);
        assertEquals(marksBox.getMyMarksMap().get(player).intValue(), 2);
        marksBox.setMyMarksMap(player, 2);
        assertEquals(marksBox.getMyMarksMap().get(player).intValue(), 4);
    }
}