package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void getMapname() {
        Map Maptest = new Map();
        assertEquals(Maptest.getMapName(),null);
    }

    @Test
    void setMapname() {
        Map Maptest = new Map();
        Maptest.setMapName("testmapname");
        assertEquals(Maptest.getMapName(),"testmapname");
    }
}