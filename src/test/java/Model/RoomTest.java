//Author: Alex Saletti
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTest {

    @Test
    void addCellslist() {
        Room testroom = new Room(1);
        Cell c = new Cell(1);
        testroom.addCellsList(c);
        assertEquals(testroom.getCellsList().get(0).getCellId(), 1);
    }

    @Test
    void getCelllist() {
        Room TestRoom = new Room(1);
        assertEquals(TestRoom.getCellsList().isEmpty(), true);
    }

    @Test
    void getRoomId() {
        Room TestRoom = new Room(1);
        assertEquals(TestRoom.getRoomId(), 1);
    }
}