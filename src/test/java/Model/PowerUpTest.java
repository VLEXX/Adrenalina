package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}