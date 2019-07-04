/**
 * @author federico Scatà
 */
package model.gamedata;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateSelectedModeTest {

    @Test
    void getSelectedMode() throws RemoteException {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedmode(Mode.BASE);
        assertEquals(m.getSelectedmode(), Mode.BASE);
    }

    @Test
    void setSelectedMode() throws RemoteException {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedmode(Mode.BASE);
        assertEquals(m.getSelectedmode(), Mode.BASE);
    }
}