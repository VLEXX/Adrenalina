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
        int[] t = new int[]{0,0,0,0,0};
        cs.setScore(t);
        assertEquals(cs.getScore(), t);
    }

    @Test
    void getChart() {
        ChartScore cs = new ChartScore();
        assertEquals(cs.getChart()[0],null );
        assertEquals(cs.getChart()[1],null );
        assertEquals(cs.getChart()[2],null );
        assertEquals(cs.getChart()[3],null );
        assertEquals(cs.getChart()[4],null );
    }

    @Test
    void setChart() {
        ChartScore cs = new ChartScore();
        Player[] t = new Player[]{Player.Yellow, Player.Purple, Player.Green, Player.Blue, Player.Black};
        cs.setChart(t);
        assertEquals(cs.getChart(), t);
    }
}