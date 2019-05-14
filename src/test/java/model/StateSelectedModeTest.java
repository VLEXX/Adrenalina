//Author: Federico Scatà
package model;

import model.gamedata.Mode;
import model.gamedata.StateSelectedMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateSelectedModeTest {

    @Test
    void getSelectedMode() {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedmode(Mode.BASE);
        assertEquals(m.getSelectedmode(), Mode.BASE);
    }

    @Test
    void setSelectedMode() {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedmode(Mode.BASE);
        assertEquals(m.getSelectedmode(), Mode.BASE);
    }
}