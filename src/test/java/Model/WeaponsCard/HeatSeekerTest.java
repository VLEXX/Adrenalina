/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeatSeekerTest {

    @Test
    void isFirstUse() {
        HeatSeeker h = new HeatSeeker();
        assertEquals(h.isFirstUse(), true);
    }

    @Test
    void getPriceToPay1() {
        HeatSeeker h = new HeatSeeker();
        assertEquals(h.getPriceToPay1(), 2);
    }

    @Test
    void getMunitionsToPay1() {
        HeatSeeker h = new HeatSeeker();
        assertEquals(h.getMunitionsToPay1(), Munitions.RED);
    }

    @Test
    void getPriceToPay2() {
        HeatSeeker h = new HeatSeeker();
        assertEquals(h.getPriceToPay2(), 1);
    }

    @Test
    void getMunitionsToPay2() {
        HeatSeeker h = new HeatSeeker();
        assertEquals(h.getMunitionsToPay2(), Munitions.YELLOW);
    }
}