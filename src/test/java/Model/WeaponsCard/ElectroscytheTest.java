/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectroscytheTest {

    @Test
    void isFirstUse() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.isFirstUse(), true);
    }

    @Test
    void getPriceToPay() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.getPriceToPay(), 1);
    }

    @Test
    void getMunitionsToPay() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.getMunitionsToPay(), Munitions.BLUE);
    }

    @Test
    void getPriceToPayReaper1() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.getPriceToPayReaper1(), 1);
    }

    @Test
    void getMunitionsToPayReaper1() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.getMunitionsToPayReaper1(), Munitions.BLUE);
    }

    @Test
    void getPriceToPayReaper2() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.getPriceToPayReaper2(), 1);
    }

    @Test
    void getMunitionsToPayReaper2() {
        Electroscythe e = new Electroscythe();
        assertEquals(e.getMunitionsToPayReaper2(), Munitions.RED);
    }
}