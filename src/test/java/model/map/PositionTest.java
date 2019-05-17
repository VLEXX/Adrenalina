//Author: Federico Scatà
package model.map;

import model.map.Cell;
import model.map.Position;
import model.map.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void getCurrentCell() {
        Position p = new Position();
        assertEquals(p.getCurrentcell(), null);
    }

    @Test
    void setCurrentCell() {
        Position p = new Position();
        Cell c = new Cell(1);
        p.setCurrentcell(c);
        assertEquals(p.getCurrentcell(), c);
    }

    @Test
    void getCurrentRoom() {
        Position p = new Position();
        assertEquals(p.getCurrentroom(), null);
    }

    @Test
    void setCurrentRoom() {
        Position p = new Position();
        Room r = new Room(1);
        p.setCurrentroom(r);
        assertEquals(p.getCurrentroom(), r);
    }
}