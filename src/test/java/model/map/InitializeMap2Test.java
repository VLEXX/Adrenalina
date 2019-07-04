/**
 * @author Alex Saletti
 */
package model.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializeMap2Test {

    @Test
    void initializeMap() {
        InitializeMap2 map2test = new InitializeMap2();
        Map map2 = map2test.initializeMap();

        assertEquals(map2.getRoomList().get(0).getRoomId(), 1);
        assertEquals(map2.getRoomList().get(1).getRoomId(), 2);
        assertEquals(map2.getRoomList().get(2).getRoomId(), 3);
        assertEquals(map2.getRoomList().get(3).getRoomId(), 4);
        assertEquals(map2.getRoomList().get(4).getRoomId(), 5);

        assertEquals(map2.getRoomList().get(0).getCellsList().get(0).getCellId(), 1);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(1).getCellId(), 4);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(0).getCellId(), 2);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(1).getCellId(), 3);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(0).getCellId(), 5);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(1).getCellId(), 6);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(0).getCellId(), 7);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(1).getCellId(), 11);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(0).getCellId(), 8);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(1).getCellId(), 9);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(2).getCellId(), 10);

        assertEquals(map2.getRoomList().get(0).getCellsList().get(0).getUpCell(), null);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(0).getDownCell().getCellId(), 4);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(0).getRightCell().getCellId(), 2);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(1).getUpCell().getCellId(), 1);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(1).getDownCell().getCellId(), 8);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(1).getLeftCell(), null);
        assertEquals(map2.getRoomList().get(0).getCellsList().get(1).getRightCell(), null);

        assertEquals(map2.getRoomList().get(1).getCellsList().get(0).getUpCell(), null);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(0).getDownCell().getCellId(), 5);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(0).getLeftCell().getCellId(), 1);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(0).getRightCell().getCellId(), 3);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(1).getUpCell(), null);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(1).getDownCell().getCellId(), 6);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(1).getLeftCell().getCellId(), 2);
        assertEquals(map2.getRoomList().get(1).getCellsList().get(1).getRightCell(), null);

        assertEquals(map2.getRoomList().get(2).getCellsList().get(0).getUpCell().getCellId(), 2);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(0).getDownCell().getCellId(), 9);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(0).getRightCell().getCellId(), 6);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(1).getUpCell().getCellId(), 3);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(1).getDownCell(), null);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(1).getLeftCell().getCellId(), 5);
        assertEquals(map2.getRoomList().get(2).getCellsList().get(1).getRightCell().getCellId(), 7);

        assertEquals(map2.getRoomList().get(3).getCellsList().get(0).getUpCell(), null);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(0).getDownCell().getCellId(), 11);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(0).getLeftCell().getCellId(), 6);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(0).getRightCell(), null);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(1).getUpCell().getCellId(), 7);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(1).getDownCell(), null);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(1).getLeftCell().getCellId(), 10);
        assertEquals(map2.getRoomList().get(3).getCellsList().get(1).getRightCell(), null);

        assertEquals(map2.getRoomList().get(4).getCellsList().get(0).getUpCell().getCellId(), 4);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(0).getDownCell(), null);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(0).getRightCell().getCellId(), 9);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(1).getUpCell().getCellId(), 5);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(1).getDownCell(), null);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(1).getLeftCell().getCellId(), 8);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(1).getRightCell().getCellId(), 10);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(2).getUpCell(), null);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(2).getDownCell(), null);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(2).getLeftCell().getCellId(), 9);
        assertEquals(map2.getRoomList().get(4).getCellsList().get(2).getRightCell().getCellId(), 11);
    }
}