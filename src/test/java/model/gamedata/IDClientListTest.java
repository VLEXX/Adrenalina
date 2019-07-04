/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.Player;
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

    @Test
    void getPlayerNick() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.putPlayerToken(Player.BLUE, 1234);
        assertEquals(idClientList.getPlayerNick(Player.BLUE), 1234);
        idClientList.removePlayerToken(Player.BLUE);
    }

    @Test
    void setSleepPlayer() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.setSleepPlayer(Player.BLUE);
        assertEquals(idClientList.getSleepPlayer(), Player.BLUE);
    }

    @Test
    void isInPlayerList() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        assertEquals(idClientList.isInPlayerList("ciao"), false);
        idClientList.addPlayerInList("ciao");
        assertEquals(idClientList.isInPlayerList("ciao"), true);
    }

    @Test
    void getPlayerFromNick() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.addNickPlayer("ciao", Player.BLUE);
        assertEquals(idClientList.getPlayerFromNick("ciao"), Player.BLUE);
    }

    @Test
    void addPlayerRMI() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.addPlayerRMI(Player.BLUE);
        assertEquals(idClientList.getPlayerRMI().get(0), Player.BLUE);
    }

    @Test
    void addPlayerInList() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.addNickPlayer("ciao", Player.BLUE);
        idClientList.addPlayerInList("ciao");
        assertEquals(idClientList.getPlayerArrayList().contains(Player.BLUE),true );
    }

    @Test
    void addConnection() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        idClientList.addConnection(Player.BLUE);
        assertEquals(idClientList.getConnection().get(Player.BLUE), true);
        idClientList.replaceConnection(Player.BLUE, false);
        assertEquals(idClientList.getConnection().get(Player.BLUE), false);
    }

    @Test
    void getNickPlayer() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        assertEquals(idClientList.getNickPlayer().isEmpty(), true);
    }

    @Test
    void getNicknameList() throws RemoteException {
        IDClientList idClientList = new IDClientList();
        assertEquals(idClientList.getNicknameList().isEmpty(), true);
    }
}