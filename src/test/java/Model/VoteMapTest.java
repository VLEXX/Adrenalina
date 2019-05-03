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

    @Test
    void addPlayerCounter(){
        VoteMap m = new VoteMap();
        m.addPlayerCounter();
        assertEquals(m.getPlayerCounter(),1);
    }

    @Test
    void getPlayerCounter(){
        VoteMap m = new VoteMap();
        assertEquals(m.getPlayerCounter(), 0);
    }

    @Test
    void decreasePlayerCounter(){
        VoteMap m = new VoteMap();
        m.addPlayerCounter();
        m.addPlayerCounter();
        m.decreasePlayerCounter();
        assertEquals(m.getPlayerCounter(), 1);
    }

    @Test
    void getInitMap(){
        VoteMap m = new VoteMap();
        assertEquals(m.getInitMap(), false);
    }

    @Test
    void setInitMap(){
        VoteMap m = new VoteMap();
        m.setInitmap();
        assertEquals(m.getInitMap(), true);
        m.setInitmap();
        assertEquals(m.getInitMap(), true);
    }
}