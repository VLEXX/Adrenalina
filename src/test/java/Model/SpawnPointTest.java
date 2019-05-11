//Author: Alex Saletti
package Model;

import Model.WeaponsCard.LockRifle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpawnPointTest {

    @Test
    void addWeapon() {
        SpawnPoint SpawnTest = new SpawnPoint();
        LockRifle lockRifle = new LockRifle();
        for (int i = 0; i < 3; i++) {
            SpawnTest.addWeapon(lockRifle, i);
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], lockRifle);
        }
    }

    @Test
    void removeWeapon() {
        SpawnPoint SpawnTest = new SpawnPoint();
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
        SpawnPoint SpawnTest = new SpawnPoint();
        for (int i = 0; i < 3; i++) {
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], null);
        }
    }
}