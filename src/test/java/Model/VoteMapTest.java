package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoteMapTest {

    @Test
    void getFinalResult() {
        VoteMap m = new VoteMap();
        assertEquals(m.getFinalResult(),-1);
    }

    @Test
    void setFinalResult() {
        VoteMap m = new VoteMap();
        m.setFinalResult(2);
        assertEquals(m.getFinalResult(),2);
    }

    @Test
    void getVoteResult() {
        VoteMap m = new VoteMap();
        assertEquals(m.getVoteResult()[0], 0);
        assertEquals(m.getVoteResult()[1], 0);
        assertEquals(m.getVoteResult()[2], 0);
        assertEquals(m.getVoteResult()[3], 0);
    }

    @Test
    void setVoteResult() {
        VoteMap m = new VoteMap();
        m.setVoteResult(1);
        m.setVoteResult(1);
        m.setVoteResult(2);
        assertEquals(m.getVoteResult()[0], 0);
        assertEquals(m.getVoteResult()[1], 2);
        assertEquals(m.getVoteResult()[2], 1);
        assertEquals(m.getVoteResult()[3], 0);
    }
}