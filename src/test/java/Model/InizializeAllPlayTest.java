//Author: Giulia Rivara
package Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InizializeAllPlayTest{

    @Test
    void getChartScore() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getChartScore().size(), 0);
    }

    @Test
    void getVoteMap() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getVoteMap().getFinalresult(), -1);
    }

    @Test
    void getCurrentPlayerState() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getCurrentPlayerState().size(), 0);
    }

    @Test
    void getCurrentTurnState() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getCurrentTurnState().size(), 0);
    }

    @Test
    void getStateSelectedMap() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getStateSelectedMap().getSelectedmap(), null);
    }

    @Test
    void getStateSelectedMode() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getStateSelectedMode().getSelectedmode(), null);
    }

    @Test
    void getVoteMode() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getVoteMode().getFinalResult(), -1);
    }

    @Test
    void getIdClientList() {
        InizializeAllPlay inizializeAllPlay = new InizializeAllPlay();
        assertEquals(inizializeAllPlay.getIdClientList().size(), 0);
    }
}