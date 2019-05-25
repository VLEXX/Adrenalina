//Author: Federico Scatà
package model.gamedata;

import model.gamedata.VoteMap;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteMapTest {

    @Test
    void getFinalResult() throws RemoteException {
        VoteMap m = new VoteMap();
        assertEquals(m.getFinalresult(), -1);
    }

    @Test
    void setFinalResult() throws RemoteException {
        VoteMap m = new VoteMap();
        m.setVoteresult(0);
        m.setVoteresult(1);
        m.setVoteresult(2);
        m.setVoteresult(2);
        m.setVoteresult(3);
        m.setInitmap();
        m.setFinalresult();
        assertEquals(m.getFinalresult(), 2);
    }

    @Test
    void getVoteResult() throws RemoteException {
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

    @Test
    void addPlayerCounter() throws RemoteException {
        VoteMap m = new VoteMap();
        m.addPlayerCounter();
        assertEquals(m.getPlayerCounter(), 1);
    }

    @Test
    void getPlayerCounter() throws RemoteException {
        VoteMap m = new VoteMap();
        assertEquals(m.getPlayerCounter(), 0);
    }

    @Test
    void decreasePlayerCounter() throws RemoteException {
        VoteMap m = new VoteMap();
        m.addPlayerCounter();
        m.addPlayerCounter();
        m.decreasePlayerCounter();
        assertEquals(m.getPlayerCounter(), 1);
    }

    @Test
    void getInitMap() throws RemoteException {
        VoteMap m = new VoteMap();
        assertEquals(m.getInitMap(), false);
    }

    @Test
    void setInitMap() throws RemoteException {
        VoteMap m = new VoteMap();
        m.setInitmap();
        assertEquals(m.getInitMap(), true);
        m.setInitmap();
        assertEquals(m.getInitMap(), true);
    }
}