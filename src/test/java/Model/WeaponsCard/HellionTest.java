/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HellionTest {

    @Test
    void isFirstUse() {
        Hellion h = new Hellion();
        assertEquals(h.isFirstUse(), true);
    }

    @Test
    void getPriceToPay1() {
        Hellion h = new Hellion();
        assertEquals(h.getPriceToPay1(), 1);
    }

    @Test
    void getMunitionsToPay1() {
        Hellion h = new Hellion();
        assertEquals(h.getMunitionsToPay1(), Munitions.YELLOW);
    }

    @Test
    void getPriceToPay2() {
        Hellion h = new Hellion();
        assertEquals(h.getPriceToPay2(), 1);
    }

    @Test
    void getMunitionsToPay2() {
        Hellion h = new Hellion();
        assertEquals(h.getMunitionsToPay2(), Munitions.RED);
    }

    @Test
    void getPriceToPayNanoTracers() {
        Hellion h = new Hellion();
        assertEquals(h.getPriceToPayNanoTracers(), 1);
    }

    @Test
    void getMunitionsToPayNanoTracers() {
        Hellion h = new Hellion();
        assertEquals(h.getMunitionsToPayNanoTracers(), Munitions.RED);
    }
}