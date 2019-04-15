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
    void setCellId() {
        Cell TestCellA = new Cell(1);
        TestCellA.setCellId(6);
        assertEquals(TestCellA.getCellId(), 6);
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
}