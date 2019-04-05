//Author: Alex Saletti
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpawnPointTest {

    @Test
    void addWeapon() {
        SpawnPoint SpawnTest = new SpawnPoint();
        for (int i = 0; i < 3; i++){
            SpawnTest.addWeapon(Weapons.ELECTROSCYTHE, i);
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], Weapons.ELECTROSCYTHE);
    }
    }

    @Test
    void removeWeapon() {
        SpawnPoint SpawnTest = new SpawnPoint();
        for (int i = 0; i < 3; i++) {
            SpawnTest.addWeapon(Weapons.ELECTROSCYTHE, i);
            assertEquals(SpawnTest.removeWeapon(i), Weapons.ELECTROSCYTHE);
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], null);
        }
    }

    @Test
    void getSpawnWeaponsList() {
        SpawnPoint SpawnTest = new SpawnPoint();
        for(int i=0;i<3;i++){
            assertEquals(SpawnTest.getSpawnWeaponsList()[i], null);
        }
    }
}