package model.gamedata;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class VoteFrenzyTest {

    @Test
    void setNum() throws RemoteException {
        VoteFrenzy voteFrenzy = new VoteFrenzy();
        voteFrenzy.setNum(5);
        voteFrenzy.decreaseNum();
        assertEquals(voteFrenzy.getNum(), 4);
    }

    @Test
    void getResult() throws RemoteException {
        VoteFrenzy voteFrenzy = new VoteFrenzy();
        assertEquals(voteFrenzy.getResult(), false);
        voteFrenzy.addYes();
        voteFrenzy.addYes();
        voteFrenzy.addYes();
        voteFrenzy.addNo();
        assertEquals(voteFrenzy.getResult(), true);
    }
}