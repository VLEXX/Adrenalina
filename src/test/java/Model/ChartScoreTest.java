//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        cs.setScore(Player.Yellow, 5);
        assertEquals(cs.getScore()[0], 5);
        cs.setScore(Player.Purple, 5);
        assertEquals(cs.getScore()[1], 5);
        cs.setScore(Player.Green, 5);
        assertEquals(cs.getScore()[2], 5);
        cs.setScore(Player.Blue, 5);
        assertEquals(cs.getScore()[3], 5);
        cs.setScore(Player.Black, 5);
        assertEquals(cs.getScore()[4], 5);
    }
}