/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FurnaceTest {

    @Test
    void isFirstUse() {
        Furnace f = new Furnace();
        assertEquals(f.isFirstUse(), true);
    }

    @Test
    void getPriceToPay1() {
        Furnace f = new Furnace();
        assertEquals(f.getPriceToPay1(), 1);
    }

    @Test
    void getMunitionsToPay1() {
        Furnace f = new Furnace();
        assertEquals(f.getMunitionsToPay1(), Munitions.BLUE);
    }

    @Test
    void getPriceToPay2() {
        Furnace f = new Furnace();
        assertEquals(f.getPriceToPay2(), 1);
    }

    @Test
    void getMunitionsToPay2() {
        Furnace f = new Furnace();
        assertEquals(f.getMunitionsToPay2(), Munitions.RED);
    }
}