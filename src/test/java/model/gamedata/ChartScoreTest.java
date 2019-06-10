//Author: Federico Scatà
package model.gamedata;

import model.gamedata.ChartScore;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChartScoreTest {

    @Test
    void getScore() {
        ChartScore cs = new ChartScore();
        assertEquals(cs.getScore()[0], 0);
        assertEquals(cs.getScore()[1], 0);
        assertEquals(cs.getScore()[2], 0);
        assertEquals(cs.getScore()[3], 0);
        assertEquals(cs.getScore()[4], 0);
    }

    @Test
    void setScore() {
        ChartScore cs = new ChartScore();
        cs.setScore(Player.YELLOW, 5);
        assertEquals(cs.getScore()[0], 5);
        cs.setScore(Player.PURPLE, 5);
        assertEquals(cs.getScore()[1], 5);
        cs.setScore(Player.GREEN, 5);
        assertEquals(cs.getScore()[2], 5);
        cs.setScore(Player.BLUE, 5);
        assertEquals(cs.getScore()[3], 5);
        cs.setScore(Player.BLACK, 5);
        assertEquals(cs.getScore()[4], 5);
    }

    @Test
    void deepClone() throws IOException, ClassNotFoundException {
        ChartScore cs = new ChartScore();
        ChartScore clone = cs.deepClone();
        assertEquals(clone.getScore()[0],0);
        assertEquals(clone.getScore()[1],0);
        assertEquals(clone.getScore()[2],0);
        assertEquals(clone.getScore()[3],0);
        assertEquals(clone.getScore()[4],0);
    }
}