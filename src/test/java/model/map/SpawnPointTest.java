//Author: Alex Saletti
package model.map;

import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.weaponscard.LockRifle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpawnPointTest {

    @Test
    void addWeapon() {
        SpawnPoint SpawnTest = new SpawnPoint(Munitions.RED);
        LockRifle lockRifle = new LockRifle();
        for (int i = 0; i < 3; i++) {
            SpawnTest.addWeapon(lockRifle, i);
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], lockRifle);
        }
    }

    @Test
    void removeWeapon() {
        SpawnPoint SpawnTest = new SpawnPoint(Munitions.RED);
        LockRifle lockRifle = new LockRifle();
        for (int i = 0; i < 3; i++) {
            SpawnTest.addWeapon(lockRifle, i);
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], lockRifle);
            SpawnTest.removeWeapon(i);
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], null);
        }
    }

    @Test
    void getSpawnWeaponsList() {
        SpawnPoint SpawnTest = new SpawnPoint(Munitions.RED);
        for (int i = 0; i < 3; i++) {
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], null);
        }
    }

    @Test
    void getSpawnColor() {
        SpawnPoint spawnPoint = new SpawnPoint(Munitions.RED);
        assertEquals(spawnPoint.getSpawnColor(), Munitions.RED);
    }

    @Test
    void getSP() {
        SpawnPoint spawnPoint = new SpawnPoint(Munitions.BLUE);
        spawnPoint.getSPDamage().add(Player.BLACK);
        assertEquals(spawnPoint.getSPDamage().get(0),Player.BLACK);
    }
}