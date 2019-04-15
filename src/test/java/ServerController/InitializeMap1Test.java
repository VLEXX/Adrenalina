//Author: Alex Saletti
package ServerController;

import Model.Cell;
import Model.Map;
import Model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializeMap1Test {

    @Test
    void initializeMap() {
        InitializeMap1 map1test = new InitializeMap1();
        Map map1 = map1test.initializeMap();


        assertEquals(map1.getRoomList().get(0).getRoomId(), 1);
        assertEquals(map1.getRoomList().get(1).getRoomId(), 2);
        assertEquals(map1.getRoomList().get(2).getRoomId(), 3);
        assertEquals(map1.getRoomList().get(3).getRoomId(), 4);
        assertEquals(map1.getRoomList().get(4).getRoomId(), 5);

        assertEquals(map1.getRoomList().get(0).getCellsList().get(0).getCellId(), 1);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(1).getCellId(), 2);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(2).getCellId(), 3);
        assertEquals(map1.getRoomList().get(1).getCellsList().get(0).getCellId(), 4);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(0).getCellId(), 5);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(1).getCellId(), 6);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(0).getCellId(), 7);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(1).getCellId(), 8);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(2).getCellId(), 10);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(3).getCellId(), 11);
        assertEquals(map1.getRoomList().get(4).getCellsList().get(0).getCellId(), 9);

        assertEquals(map1.getRoomList().get(0).getCellsList().get(0).getUpCell(), null);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(0).getDownCell().getCellId(), 5);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(0).getRightCell().getCellId(), 2);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(1).getUpCell(), null);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(1).getDownCell(), null);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(1).getLeftCell().getCellId(), 1);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(1).getRightCell().getCellId(), 3);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(2).getUpCell(), null);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(2).getDownCell().getCellId(), 7);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(2).getLeftCell().getCellId(), 2);
        assertEquals(map1.getRoomList().get(0).getCellsList().get(2).getRightCell().getCellId(), 4);

        assertEquals(map1.getRoomList().get(1).getCellsList().get(0).getUpCell(), null);
        assertEquals(map1.getRoomList().get(1).getCellsList().get(0).getDownCell().getCellId(), 8);
        assertEquals(map1.getRoomList().get(1).getCellsList().get(0).getLeftCell().getCellId(), 3);
        assertEquals(map1.getRoomList().get(1).getCellsList().get(0).getRightCell(), null);

        assertEquals(map1.getRoomList().get(2).getCellsList().get(0).getUpCell().getCellId(), 1);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(0).getDownCell(), null);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(0).getRightCell().getCellId(), 6);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(1).getUpCell(), null);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(1).getDownCell().getCellId(), 9);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(1).getLeftCell().getCellId(), 5);
        assertEquals(map1.getRoomList().get(2).getCellsList().get(1).getRightCell(), null);

        assertEquals(map1.getRoomList().get(3).getCellsList().get(0).getUpCell().getCellId(), 3);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(0).getDownCell().getCellId(), 10);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(0).getRightCell().getCellId(), 8);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(1).getUpCell().getCellId(), 4);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(1).getDownCell().getCellId(), 11);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(1).getLeftCell().getCellId(), 7);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(1).getRightCell(), null);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(2).getUpCell().getCellId(), 7);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(2).getDownCell(), null);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(2).getLeftCell().getCellId(), 9);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(2).getRightCell().getCellId(), 11);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(3).getUpCell().getCellId(), 8);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(3).getDownCell(), null);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(3).getLeftCell().getCellId(), 10);
        assertEquals(map1.getRoomList().get(3).getCellsList().get(3).getRightCell(), null);

        assertEquals(map1.getRoomList().get(4).getCellsList().get(0).getUpCell().getCellId(), 6);
        assertEquals(map1.getRoomList().get(4).getCellsList().get(0).getDownCell(), null);
        assertEquals(map1.getRoomList().get(4).getCellsList().get(0).getLeftCell(), null);
        assertEquals(map1.getRoomList().get(4).getCellsList().get(0).getRightCell().getCellId(), 10);


    }
    }