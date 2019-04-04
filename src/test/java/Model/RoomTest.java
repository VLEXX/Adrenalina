//Author: Alex Saletti
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void setCellslistElem() {
       Room TestRoom = new Room();
       Cell testcellA = new Cell(1);
       for(int i=0;i<4;i++){
           TestRoom.setCellslistElem( testcellA , i);
           assertEquals(TestRoom.getCellslistElem(i), testcellA);
       }
    }

    @Test
    void getCellslistElem() {
        Room TestRoom = new Room();
        for (int i=0;i<4;i++) {
            assertEquals(TestRoom.getCellslistElem(i), null);
        }
    }
}