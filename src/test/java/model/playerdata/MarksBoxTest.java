/**
 * @author Giulia Rivara
 */
package model.playerdata;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

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
        assertEquals(marksBox.getMyMarksMap().get(player).intValue(), 3);
    }

    @Test
    void setMyMarksMap2() {
        Player player = Player.YELLOW;
        int marks = 1;
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(player, marks);
        assertEquals(marksBox.getMyMarksMap().size(), 1);
        assertEquals(marksBox.getMyMarksMap().get(player).intValue(), 1);
        marksBox.setMyMarksMap(player, 2);
        assertEquals(marksBox.getMyMarksMap().get(player).intValue(), 3);
    }

    @Test
    void deepCloneMarks() throws IOException, ClassNotFoundException {
        MarksBox marksBox = new MarksBox();
        HashMap<Player, Integer> playerIntegerHashMap;
        marksBox.setMyMarksMap(Player.YELLOW, 1);
        playerIntegerHashMap = marksBox.deepCloneMarks();
        assertEquals(playerIntegerHashMap.get(Player.YELLOW), 1);
    }
}