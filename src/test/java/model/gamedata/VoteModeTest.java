/**
 * @author Federico Scatà
 */
package model.gamedata;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteModeTest {

    @Test
    void getFinalResult() throws RemoteException {
        VoteMode m = new VoteMode();
        assertEquals(m.getFinalResult(), null);
    }

    @Test
    void setFinalResult() throws RemoteException {
        VoteMode m = new VoteMode();
        m.setFinalResult();
        assertEquals(m.getFinalResult(), Mode.BASE);
        m.setVoteResult(1);
        m.setFinalResult();
        assertEquals(m.getFinalResult(), Mode.DOMINATION);
    }

    @Test
    void getVoteResult() throws RemoteException {
        VoteMode m = new VoteMode();
        assertEquals(m.getVoteResult()[0], 0);
        assertEquals(m.getVoteResult()[1], 0);
    }

    @Test
    void setVoteResult() throws RemoteException {
        VoteMode m = new VoteMode();
        m.setVoteResult(1);
        m.setVoteResult(1);
        assertEquals(m.getVoteResult()[0], 0);
        assertEquals(m.getVoteResult()[1], 2);
    }
}