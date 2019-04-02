//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateSelectedModeTest {

    @Test
    void getSelectedMode() {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedMode(Mode.Base);
        assertEquals(m.getSelectedMode(), Mode.Base);
    }

    @Test
    void setSelectedMode() {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedMode(Mode.Base);
        assertEquals(m.getSelectedMode(), Mode.Base);
    }
}