/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponsDadTest {

    @Test
    void getFirstPrice() {
        WeaponsDad w = new WeaponsDad();
        w.setFirstPrice(Munitions.BLUE, 3);
        assertEquals(w.getFirstPrice().get(Munitions.BLUE), 3);
    }

    @Test
    void getSecondPrice() {
        WeaponsDad w = new WeaponsDad();
        w.setSecondPrice(Munitions.BLUE, 0);
        assertEquals(w.getSecondPrice().get(Munitions.BLUE), 0);
    }

    @Test
    void getThirdPrice() {
        WeaponsDad w = new WeaponsDad();
        w.setThirdPrice(Munitions.BLUE, 3);
        assertEquals(w.getThirdPrice().get(Munitions.BLUE), 3);
    }

    @Test
    void getFirstUse() {
        WeaponsDad w = new WeaponsDad();
        w.setFirstUse(false);
        assertEquals(w.getFirstUse(), false);
    }
}