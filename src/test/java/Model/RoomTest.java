//Author: Alex Saletti
package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void setCellslist() {
       Room testroom = new Room();
       ArrayList<Cell> cell = new ArrayList<>();
       Cell c = new Cell(23);
       cell.add(c);
       testroom.setCellsList(cell);
       assertEquals(testroom.getCellsList().get(0).getCellId(), 23 );
    }

    @Test
    void getCelllist() {
        Room TestRoom = new Room();
        assertEquals(TestRoom.getCellsList().isEmpty(), true);
    }
}