package View;

import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewChartScoreTest {

    @Test
    void getScore() {
        ViewChartScore cs = new ViewChartScore();
        assertEquals(cs.getScore()[0], 0);
        assertEquals(cs.getScore()[1], 0);
        assertEquals(cs.getScore()[2], 0);
        assertEquals(cs.getScore()[3], 0);
        assertEquals(cs.getScore()[4], 0);
    }

    @Test
    void setScore() {
        ViewChartScore cs = new ViewChartScore();
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
}