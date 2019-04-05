//Author: Alex Salette
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getUpCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getUpcell(),null);

    }

    @Test
    void getDownCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getDowncell(),null);
    }

    @Test
    void getLeftCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getLeftcell(),null);
    }

    @Test
    void getRightCell() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getRightcell(),null);
    }

    @Test
    void setUpCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setUpcell(testcellB);
        assertEquals(testcellA.getUpcell(),testcellB);

    }

    @Test
    void setDownCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setDowncell(testcellB);
        assertEquals(testcellA.getDowncell(),testcellB);
    }

    @Test
    void setLeftCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setLeftcell(testcellB);
        assertEquals(testcellA.getLeftcell(),testcellB);
    }

    @Test
    void setRightCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setRightcell(testcellB);
        assertEquals(testcellA.getRightcell(),testcellB);
    }

    @Test
    void getCellId() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getCellId(),1);
    }

    @Test
    void setCellId() {
        Cell testcellA = new Cell(1);
        testcellA.setCellId(6);
        assertEquals(testcellA.getCellId(), 6);
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