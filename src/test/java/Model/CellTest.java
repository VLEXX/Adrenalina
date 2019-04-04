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
    void setUpCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setUpCell(testcellB);
        assertEquals(testcellA.getUpCell(),testcellB);

    }

    @Test
    void setDownCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setDownCell(testcellB);
        assertEquals(testcellA.getDownCell(),testcellB);
    }

    @Test
    void setLeftCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setLeftCell(testcellB);
        assertEquals(testcellA.getLeftCell(),testcellB);
    }

    @Test
    void setRightCell() {
        Cell testcellA = new Cell(1);
        Cell testcellB = new Cell(2);
        testcellA.setRightCell(testcellB);
        assertEquals(testcellA.getRightCell(),testcellB);
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
        assertEquals(testcellA.getAmmoHere(),null);

    }

    @Test
    void setAmmoHere() {
        Cell testcellA = new Cell(1);
        Ammo testAmmo = new Ammo();
        testcellA.setAmmoHere(testAmmo);
        assertEquals(testcellA.getAmmoHere(),testAmmo);

    }

    @Test
    void getSpawnPointColor() {
        Cell testcellA = new Cell(1);
        assertEquals(testcellA.getSpawnPointZone(),null);

    }

    @Test
    void setSpawnPointColor() {
        Cell testcellA = new Cell(1);
        SpawnPoint spawntest = new SpawnPoint();
        testcellA.setSpawnPointZone(spawntest);
        assertEquals(testcellA.getSpawnPointZone(),spawntest);

    }
}