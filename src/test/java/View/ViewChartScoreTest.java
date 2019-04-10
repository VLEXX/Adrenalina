package View;

import Model.ChartScore;
import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewChartScoreTest {

    @Test
    void getChartScore() {
        ViewChartScore v = new ViewChartScore();
        assertEquals(v.getChartScore().getScore()[0], 0);
        assertEquals(v.getChartScore().getScore()[1], 0);
        assertEquals(v.getChartScore().getScore()[2], 0);
        assertEquals(v.getChartScore().getScore()[3], 0);
        assertEquals(v.getChartScore().getScore()[4], 0);
    }

    @Test
    void setChartScore() {
        ViewChartScore v = new ViewChartScore();
        ChartScore c = new ChartScore();
        c.setScore(Player.YELLOW, 12);
        c.setScore(Player.PURPLE, 22);
        c.setScore(Player.GREEN, 32);
        c.setScore(Player.BLUE, 42);
        c.setScore(Player.BLACK, 52);
        v.setChartScore(c);
        assertEquals(v.getChartScore().getScore()[0], 12);
        assertEquals(v.getChartScore().getScore()[1], 22);
        assertEquals(v.getChartScore().getScore()[2], 32);
        assertEquals(v.getChartScore().getScore()[3], 42);
        assertEquals(v.getChartScore().getScore()[4], 52);
    }
}