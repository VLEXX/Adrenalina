//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteModeTest {

    @Test
    void getFinalResult() {
        VoteMode m = new VoteMode();
        assertEquals(m.getFinalResult(), -1);
    }

    @Test
    void setFinalResult() {
        VoteMode m = new VoteMode();
        m.setFinalResult(2);
        assertEquals(m.getFinalResult(), 2);
    }

    @Test
    void getVoteResult() {
        VoteMode m = new VoteMode();
        assertEquals(m.getVoteResult()[0], 0);
        assertEquals(m.getVoteResult()[1], 0);
        assertEquals(m.getVoteResult()[2], 0);
        assertEquals(m.getVoteResult()[3], 0);
    }

    @Test
    void setVoteResult() {
        VoteMode m = new VoteMode();
        m.setVoteResult(1);
        m.setVoteResult(1);
        m.setVoteResult(2);
        assertEquals(m.getVoteResult()[0], 0);
        assertEquals(m.getVoteResult()[1], 2);
        assertEquals(m.getVoteResult()[2], 1);
        assertEquals(m.getVoteResult()[3], 0);
    }
}