/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import java.lang.management.PlatformManagedObject;

import static org.junit.jupiter.api.Assertions.*;

class FlameThrowerTest {

    @Test
    void isFirstUse() {
        FlameThrower f = new FlameThrower();
        assertEquals(f.isFirstUse(), true);
    }

    @Test
    void getPriceToPay() {
        FlameThrower f = new FlameThrower();
        assertEquals(f.getPriceToPay(), 1);
    }

    @Test
    void getMunitionsToPay() {
        FlameThrower f = new FlameThrower();
        assertEquals(f.getMunitionsToPay(), Munitions.RED);
    }

    @Test
    void getPriceToPayBarbecue() {
        FlameThrower f = new FlameThrower();
        assertEquals(f.getPriceToPayBarbecue(), 2);
    }

    @Test
    void getMunitionsToPayBarbecue() {
        FlameThrower f = new FlameThrower();
        assertEquals(f.getMunitionsToPayBarbecue(), Munitions.YELLOW);
    }
}