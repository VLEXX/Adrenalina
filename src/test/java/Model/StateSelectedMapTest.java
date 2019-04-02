//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateSelectedMapTest {

    @Test
    void getSelectedMap() {
        StateSelectedMap m = new StateSelectedMap();
        assertEquals(m.getSelectedMap(), null);
    }

    @Test
    void setSelectedMap() {
        StateSelectedMap m = new StateSelectedMap();
        Map n = new Map();
        m.setSelectedMap(n);
        assertEquals(m.getSelectedMap(),n);
    }
}