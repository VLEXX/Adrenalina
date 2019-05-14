package model;

import model.munitions.Munitions;
import model.powerup.PowerUp;
import model.powerup.PowerUpId;
import model.weaponscard.LockRifle;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerUpTest {

    @Test
    void getId() {
        PowerUp powerUp = new PowerUp(PowerUpId.NEWTON, Munitions.RED);
        assertEquals(powerUp.getId(), PowerUpId.NEWTON);
    }

    @Test
    void getColor() {
        PowerUp powerUp = new PowerUp(PowerUpId.NEWTON, Munitions.RED);
        assertEquals(powerUp.getColor(), Munitions.RED);
    }

    @Test
    void munitionsChecker() {
        PowerUp powerUp = new PowerUp(PowerUpId.NEWTON, Munitions.BLUE);
        assertEquals(powerUp.munitionsChecker(Munitions.BLUE), 1);
        assertEquals(powerUp.munitionsChecker(Munitions.RED), 0);
    }
}