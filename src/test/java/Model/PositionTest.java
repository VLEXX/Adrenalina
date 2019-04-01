package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getCurrentCell() {
        Position p = new Position();
        assertEquals(p.getCurrentCell(), null);
    }

    @Test
    void setCurrentCell() {
        Position p = new Position();
        Cell c = new Cell();
        p.setCurrentCell(c);
        assertEquals(p.getCurrentCell(), c);
    }

    @Test
    void getCurrentRoom() {
        Position p = new Position();
        assertEquals(p.getCurrentRoom(), null);
    }

    @Test
    void setCurrentRoom() {
        Position p = new Position();
        Room r = new Room();
        p.setCurrentRoom(r);
        assertEquals(p.getCurrentRoom(), r);
    }
}