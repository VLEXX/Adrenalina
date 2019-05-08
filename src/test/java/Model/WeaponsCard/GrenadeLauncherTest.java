/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrenadeLauncherTest {

    @Test
    void isFirstUse() {
        GrenadeLauncher g = new GrenadeLauncher();
        assertEquals(g.isFirstUse(), true);
    }

    @Test
    void getPriceToPay() {
        GrenadeLauncher g = new GrenadeLauncher();
        assertEquals(g.getPriceToPay(), 1);
    }

    @Test
    void getMunitionsToPay() {
        GrenadeLauncher g = new GrenadeLauncher();
        assertEquals(g.getMunitionsToPay(), Munitions.RED);
    }

    @Test
    void getPriceToPayExtra() {
        GrenadeLauncher g = new GrenadeLauncher();
        assertEquals(g.getPriceToPayExtra(), 1);
    }

    @Test
    void getMunitionsToPayExtra() {
        GrenadeLauncher g = new GrenadeLauncher();
        assertEquals(g.getMunitionsToPayExtra(), Munitions.RED);
    }
}