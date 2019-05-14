/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeaponTest {

    @Test
    void getFirstPrice() {
        Weapon w = new LockRifle();
        w.setFirstPrice(Munitions.BLUE, 3);
        assertEquals(w.getFirstPrice().get(Munitions.BLUE), 3);
    }

    @Test
    void getSecondPrice() {
        Weapon w = new LockRifle();
        w.setSecondPrice(Munitions.BLUE, 0);
        assertEquals(w.getSecondPrice().get(Munitions.BLUE), 0);
    }

    @Test
    void getThirdPrice() {
        Weapon w = new LockRifle();
        w.setThirdPrice(Munitions.BLUE, 3);
        assertEquals(w.getThirdPrice().get(Munitions.BLUE), 3);
    }

    @Test
    void getLoaded() {
        Weapon w = new LockRifle();
        w.setLoaded(false);
        assertEquals(w.getLoaded(), false);
    }

    @Test
    void munitionChecker() {
        Weapon w = new LockRifle();
        w.setCardColor(Munitions.BLUE);
        assertEquals(w.munitionsChecker(Munitions.BLUE), 1);
        assertEquals(w.munitionsChecker(Munitions.RED), 0);
    }

    @Test
    void getCardColor() {
        Weapon w = new LockRifle();
        w.setCardColor(Munitions.BLUE);
        assertEquals(w.getCardColor(), Munitions.BLUE);
    }

    @Test
    void hasSecondAttack() {
        Weapon w = new LockRifle();
        w.setSecondAttack(true);
        assertEquals(w.hasSecondAttack(), true);
    }

    @Test
    void hasThirdAttack() {
        Weapon w = new LockRifle();
        w.setThirdAttack(true);
        assertEquals(w.hasThirdAttack(), true);
    }
}