package model.map;

import model.map.Map;
import model.map.Room;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapTest {

    @Test
    void getMapname() {
        Map maptest = new Map();
        maptest.setMapname("ciao");
        assertEquals(maptest.getMapname(), "ciao");
    }

    @Test
    void setMapname() {
        Map Maptest = new Map();
        Maptest.setMapname("testmapname");
        assertEquals(Maptest.getMapname(), "testmapname");
    }

    @Test
    void getRoomList() {
        Map m = new Map();
        assertEquals(m.getRoomList().isEmpty(), true);
    }

    @Test
    void addRoom() {
        Map m = new Map();
        Room r = new Room(3);
        m.addRoom(r);
        assertEquals(m.getRoomList().get(0), r);
    }

    @Test
    void deepClone() throws IOException, ClassNotFoundException {
        Map m = new Map();
        Map clone = m.deepClone();
        assertEquals(m.getRoomList().isEmpty(), true);
    }
}