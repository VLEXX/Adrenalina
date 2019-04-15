//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class VoteMapTest {

    @Test
    void getFinalResult() {
        VoteMap m = new VoteMap();
        assertEquals(m.getFinalresult(),-1);
    }

    @Test
    void setFinalResult() {
        VoteMap m = new VoteMap();
        m.setFinalresult(2);
        assertEquals(m.getFinalresult(),2);
    }

    @Test
    void getVoteResult() {
        VoteMap m = new VoteMap();
        assertEquals(m.getVoteresult()[0], 0);
        assertEquals(m.getVoteresult()[1], 0);
        assertEquals(m.getVoteresult()[2], 0);
        assertEquals(m.getVoteresult()[3], 0);
    }

    @Test
    void setVoteResult() throws RemoteException {
        VoteMap m = new VoteMap();
        m.setVoteresult(1);
        m.setVoteresult(1);
        m.setVoteresult(2);
        assertEquals(m.getVoteresult()[0], 0);
        assertEquals(m.getVoteresult()[1], 2);
        assertEquals(m.getVoteresult()[2], 1);
        assertEquals(m.getVoteresult()[3], 0);
    }
}