//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDClientListTest {

    @Test
    void getClientList() {
        IDClientList cl = new IDClientList();
        assertEquals(cl.getClientList()[0], 0);
        assertEquals(cl.getClientList()[1], 0);
        assertEquals(cl.getClientList()[2], 0);
        assertEquals(cl.getClientList()[3], 0);
        assertEquals(cl.getClientList()[4], 0);
    }

    @Test
    void setClientList() {
        IDClientList cl = new IDClientList();
        cl.setClientList(0, 52);
        cl.setClientList(1, 68);
        cl.setClientList(2, 1);
        cl.setClientList(3, 23);
        cl.setClientList(4, 24);
        assertEquals(cl.getClientList()[0], 52);
        assertEquals(cl.getClientList()[1], 68);
        assertEquals(cl.getClientList()[2], 1);
        assertEquals(cl.getClientList()[3], 23);
        assertEquals(cl.getClientList()[4], 24);
    }
}