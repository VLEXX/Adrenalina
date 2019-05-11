//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateSelectedMapTest {

    @Test
    void getSelectedMap() {
        StateSelectedMap m = new StateSelectedMap();
        assertEquals(m.getSelectedmap(), null);
    }

    @Test
    void setSelectedMap() {
        StateSelectedMap m = new StateSelectedMap();
        Map n = new Map();
        m.setStrategyMap(0);
        m.setSelectedmap();
        assertEquals(m.getSelectedmap().getMapname(), "1");
        m.setStrategyMap(1);
        m.setSelectedmap();
        assertEquals(m.getSelectedmap().getMapname(), "2");
        m.setStrategyMap(2);
        m.setSelectedmap();
        assertEquals(m.getSelectedmap().getMapname(), "3");
        m.setStrategyMap(3);
        m.setSelectedmap();
        assertEquals(m.getSelectedmap().getMapname(), "4");
    }
}