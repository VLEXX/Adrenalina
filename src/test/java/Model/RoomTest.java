//Author: Alex Saletti
package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void addCellslist() {
       Room testroom = new Room();
       Cell c = new Cell(1);
       testroom.addCellsList(c);
       assertEquals(testroom.getCellsList().get(0).getCellId(), 1);
    }

    @Test
    void getCelllist() {
        Room TestRoom = new Room();
        assertEquals(TestRoom.getCellsList().isEmpty(), true);
    }
}