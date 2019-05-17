package model.map;

import model.map.InitializeMap3;
import model.map.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializeMap3Test {

    @Test
    void initializeMap() {
        InitializeMap3 map3test = new InitializeMap3();
        Map map3 = map3test.initializeMap();

        assertEquals(map3.getRoomList().get(0).getRoomId(), 1);
        assertEquals(map3.getRoomList().get(1).getRoomId(), 2);
        assertEquals(map3.getRoomList().get(2).getRoomId(), 3);
        assertEquals(map3.getRoomList().get(3).getRoomId(), 4);

        assertEquals(map3.getRoomList().get(0).getCellsList().get(0).getCellId(), 1);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(1).getCellId(), 2);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(2).getCellId(), 3);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(0).getCellId(), 4);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(1).getCellId(), 5);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(2).getCellId(), 6);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(0).getCellId(), 7);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(1).getCellId(), 10);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(0).getCellId(), 8);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(1).getCellId(), 9);

        assertEquals(map3.getRoomList().get(0).getCellsList().get(0).getUpCell(), null);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(0).getDownCell().getCellId(), 4);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(0).getRightCell().getCellId(), 2);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(1).getUpCell(), null);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(1).getDownCell(), null);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(1).getLeftCell().getCellId(), 1);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(1).getRightCell().getCellId(), 3);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(2).getUpCell(), null);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(2).getDownCell().getCellId(), 6);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(2).getLeftCell().getCellId(), 2);
        assertEquals(map3.getRoomList().get(0).getCellsList().get(2).getRightCell(), null);

        assertEquals(map3.getRoomList().get(1).getCellsList().get(0).getUpCell().getCellId(), 1);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(0).getDownCell(), null);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(0).getRightCell().getCellId(), 5);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(1).getUpCell(), null);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(1).getDownCell().getCellId(), 8);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(1).getLeftCell().getCellId(), 4);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(1).getRightCell().getCellId(), 6);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(2).getUpCell().getCellId(), 3);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(2).getDownCell(), null);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(2).getLeftCell().getCellId(), 5);
        assertEquals(map3.getRoomList().get(1).getCellsList().get(2).getRightCell().getCellId(), 7);

        assertEquals(map3.getRoomList().get(2).getCellsList().get(0).getUpCell(), null);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(0).getDownCell().getCellId(), 10);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(0).getLeftCell().getCellId(), 6);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(0).getRightCell(), null);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(1).getUpCell().getCellId(), 7);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(1).getDownCell(), null);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(1).getLeftCell().getCellId(), 9);
        assertEquals(map3.getRoomList().get(2).getCellsList().get(1).getRightCell(), null);

        assertEquals(map3.getRoomList().get(3).getCellsList().get(0).getUpCell().getCellId(), 5);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(0).getDownCell(), null);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(0).getRightCell().getCellId(), 9);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(1).getUpCell(), null);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(1).getDownCell(), null);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(1).getLeftCell().getCellId(), 8);
        assertEquals(map3.getRoomList().get(3).getCellsList().get(1).getRightCell().getCellId(), 10);
    }
}