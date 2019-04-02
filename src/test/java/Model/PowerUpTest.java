//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {

    @Test
    void getPowerUpName() {
        PowerUp p = new PowerUp("name");
        assertEquals(p.getPowerUpName(), "name");
    }
}