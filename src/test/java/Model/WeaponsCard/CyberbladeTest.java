/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CyberbladeTest {

    @Test
    void isFirstUse() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.isFirstUse(), true);
    }

    @Test
    void getPriceToPay1() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.getPriceToPay1(), 1);
    }

    @Test
    void getPriceToPay2() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.getPriceToPay2(), 1);
    }

    @Test
    void getPriceToPayCrumble() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.getPriceToPayCrumble(), 1);
    }

    @Test
    void getMunitionsToPay1() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.getMunitionsToPay1(), Munitions.YELLOW);
    }

    @Test
    void getMunitionsToPay2() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.getMunitionsToPay2(), Munitions.RED);
    }

    @Test
    void getMunitionsToPayCrumble() {
        Cyberblade c = new Cyberblade();
        assertEquals(c.getMunitionsToPayCrumble(), Munitions.YELLOW);
    }
}