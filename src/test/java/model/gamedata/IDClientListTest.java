//Author: Federico Scatà
package model.gamedata;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IDClientListTest {

    @Test
    void setClientList() throws RemoteException {
        IDClientList cl = new IDClientList();
        int i = cl.addClient();
        assertEquals(cl.getClientlist().get(0), i);
        cl.addClient();
        cl.addClient();
        cl.addClient();
        cl.addClient();
        assertEquals(cl.addClient(), -1);
    }

    @Test
    void getPlayerArrayList() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        assertEquals(idClientList.getPlayerArrayList().isEmpty(), true);
    }

    @Test
    void getIndexArray() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.increaseIndexArray();
        idClientList.increaseIndexArray();
        assertEquals(idClientList.getIndexArray(),2);
        idClientList.resetIndexArray();
        assertEquals(idClientList.getIndexArray(),0);
    }
}