package model.munitions;

import model.munitions.Ammo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmmoTest {

    @Test
    void getAmmoList() {
        Ammo TestAmmo = new Ammo();
        for (int i = 0; i < 3; i++) {
            assertEquals(TestAmmo.getAmmoList()[i], 0);
        }
    }

    @Test
    void setAmmoList() {
        Ammo TestAmmo = new Ammo();
        int[] a = new int[]{3, 4, 5};
        TestAmmo.setAmmoList(a);
        assertEquals(TestAmmo.getAmmoList(), a);

    }

    @Test
    void getPossiblePowerUp() {
        Ammo TestAmmo = new Ammo();
        assertEquals(TestAmmo.getPossiblePowerUp(), false);
    }

    @Test
    void setPossiblePowerUp() {
        Ammo TestAmmo = new Ammo();
        TestAmmo.setPossiblePowerUp(true);
        assertEquals(TestAmmo.getPossiblePowerUp(), true);
    }
}