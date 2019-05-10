/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void getFirstPrice() {
        Weapon w = new Weapon();
        w.setFirstPrice(Munitions.BLUE, 3);
        assertEquals(w.getFirstPrice().get(Munitions.BLUE), 3);
    }

    @Test
    void getSecondPrice() {
        Weapon w = new Weapon();
        w.setSecondPrice(Munitions.BLUE, 0);
        assertEquals(w.getSecondPrice().get(Munitions.BLUE), 0);
    }

    @Test
    void getThirdPrice() {
        Weapon w = new Weapon();
        w.setThirdPrice(Munitions.BLUE, 3);
        assertEquals(w.getThirdPrice().get(Munitions.BLUE), 3);
    }

    @Test
    void getFirstUse() {
        Weapon w = new Weapon();
        w.setFirstUse(false);
        assertEquals(w.getFirstUse(), false);
    }

    @Test
    void munitionChecker(){
        Weapon w = new Weapon();
        Munitions m = Munitions.BLUE;
        assertEquals(w.munitionsChecker(m), Munitions.BLUE);
    }

    @Test
    void getCardColor(){
        Weapon w = new Weapon();
        w.setCardColor(Munitions.BLUE);
        assertEquals(w.getCardColor(), Munitions.BLUE);
    }

    @Test
    void hasSecondAttack() {
        Weapon w = new Weapon();
        w.setSecondAttack(true);
        assertEquals(w.hasSecondAttack(), true);
    }

    @Test
    void hasThirdAttack() {
        Weapon w = new Weapon();
        w.setThirdAttack(true);
        assertEquals(w.hasThirdAttack(), true);
    }
}