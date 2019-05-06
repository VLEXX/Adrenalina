//Author: Alex Salette
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getUpCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getUpCell(),null);

    }

    @Test
    void getDownCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getDownCell(),null);
    }

    @Test
    void getLeftCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getLeftCell(),null);
    }

    @Test
    void getRightCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getRightCell(),null);
    }

    @Test
    void setCells() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(1);
        testcellA.setCells(testcellB,testcellB,testcellB,testcellB);
        assertEquals(testcellA.getUpCell(),testcellB);
        assertEquals(testcellA.getDownCell(),testcellB);
        assertEquals(testcellA.getLeftCell(),testcellB);
        assertEquals(testcellA.getRightCell(),testcellB);
    }



    @Test
    void getCellId() {
        Cell TestCellA = new Cell(1);
        assertEquals(TestCellA.getCellId(),1);
    }


    @Test
    void getAmmoHere() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getAmmohere(),null);
    }

    @Test
    void setAmmoHere() {
        Cell testcellA = new Cell(1);
        Ammo testAmmo = new Ammo();
        testcellA.setAmmohere(testAmmo);
        assertEquals(testcellA.getAmmohere(),testAmmo);
    }

    @Test
    void getSpawnPointColor() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getSpawnpointzone(),null);
    }

    @Test
    void setSpawnPointColor() {
        Cell testcellA = new Cell(1);
        SpawnPoint spawntest = new SpawnPoint();
        testcellA.setSpawnpointzone(spawntest);
        assertEquals(testcellA.getSpawnpointzone(),spawntest);
    }

    @Test
    void getReachableCells() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        Cell cell3 = new Cell(3);
        Cell cell4 = new Cell(4);
        Cell cell5 = new Cell(5);
        cell1.setCells(cell2,cell3,cell4,cell5);
        cell1.initializeReachableCells();
        assertTrue(cell1.getReachableCells().contains(cell2));
        assertTrue(cell1.getReachableCells().contains(cell3));
        assertTrue(cell1.getReachableCells().contains(cell4));
        assertTrue(cell1.getReachableCells().contains(cell5));
    }

    @Test
    void initializeReachableCells() {
       Cell testcell = new Cell(-1);
       Cell[] cellarray;
       cellarray = new Cell[52];
       for (int i=0;i<52;i++)
           cellarray[i] = new Cell(i);
       testcell.setCells(cellarray[0],cellarray[13],cellarray[26],cellarray[39]);
       cellarray[0].setCells(cellarray[1],null,cellarray[2],cellarray[3]);
       cellarray[1].setCells(cellarray[5],null,cellarray[4],cellarray[6]);
       cellarray[2].setCells(cellarray[9],cellarray[7],cellarray[8],null);
       cellarray[3].setCells(cellarray[12],cellarray[10],null,cellarray[11]);
       cellarray[13].setCells(null,cellarray[15],cellarray[14],cellarray[16]);
       cellarray[14].setCells(cellarray[17],cellarray[19],cellarray[18],null);
       cellarray[15].setCells(null,cellarray[21],cellarray[20],cellarray[22]);
       cellarray[16].setCells(cellarray[23],cellarray[25],null,cellarray[24]);
       cellarray[26].setCells(cellarray[27],cellarray[29],cellarray[28],null);
       cellarray[27].setCells(cellarray[31],null,cellarray[30],cellarray[32]);
       cellarray[28].setCells(cellarray[33],cellarray[35],cellarray[34],null);
       cellarray[29].setCells(null,cellarray[37],cellarray[36],cellarray[38]);
       cellarray[39].setCells(cellarray[40],cellarray[42],null,cellarray[41]);
       cellarray[40].setCells(cellarray[44],null,cellarray[43],cellarray[45]);
       cellarray[41].setCells(cellarray[46],cellarray[48],null,cellarray[47]);
       cellarray[42].setCells(null,cellarray[50],cellarray[49],cellarray[51]);
       testcell.initializeReachableCells();
       for (int j=0;j<52;j++)
           assertTrue(testcell.getReachableCells().contains(cellarray[j]));
    }

    @Test
    public void getInCellPlayers() {

    }

    @Test
    public void removeInCellPlayer() {

    }

    @Test
    public void addInCellPlayer() {

    }
}