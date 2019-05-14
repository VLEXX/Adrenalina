package model;

import model.munitions.Munitions;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
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