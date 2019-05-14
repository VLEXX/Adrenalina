//Author: Federico Scatà
package model;

import model.gamedata.IDClientList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IDClientListTest {

    @Test
    void getClientList() {
        IDClientList cl = new IDClientList();
        assertEquals(cl.getClientlist()[0], 0);
        assertEquals(cl.getClientlist()[1], 0);
        assertEquals(cl.getClientlist()[2], 0);
        assertEquals(cl.getClientlist()[3], 0);
        assertEquals(cl.getClientlist()[4], 0);
    }

    @Test
    void setClientList() {
        IDClientList cl = new IDClientList();
        cl.setClientList(0, 52);
        cl.setClientList(1, 68);
        cl.setClientList(2, 1);
        cl.setClientList(3, 23);
        cl.setClientList(4, 24);
        assertEquals(cl.getClientlist()[0], 52);
        assertEquals(cl.getClientlist()[1], 68);
        assertEquals(cl.getClientlist()[2], 1);
        assertEquals(cl.getClientlist()[3], 23);
        assertEquals(cl.getClientlist()[4], 24);
    }
}