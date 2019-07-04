/**
 * @author Federico Scatà
 */
package model.map;

import org.junit.jupiter.api.Test;

import java.io.IOException;

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

    @Test
    void deepClone() throws IOException, ClassNotFoundException {
        Position position = new Position();
        position.setCurrentcell(new Cell(1));
        Position clone = position.deepClone();
        assertEquals(clone.getCurrentcell().getCellId(), 1);
    }
}