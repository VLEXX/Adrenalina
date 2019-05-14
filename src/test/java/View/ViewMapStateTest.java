package View;

import model.map.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewMapStateTest {

    @Test
    void getSelectedMap() {
        ViewMapState m = new ViewMapState();
        assertEquals(m.getSelectedMap(), null);
    }

    @Test
    void setSelectedMap() {
        ViewMapState m = new ViewMapState();
        Map map = new Map();
        m.setSelectedMap(map);
        assertEquals(m.getSelectedMap(), map);
    }
}