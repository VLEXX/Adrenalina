//Author: Giulia Rivara
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageBoxTest {

    @Test
    void setDeathCounter() {
        DamageBox d = new DamageBox();
        d.setDeathCounter(3);
        assertEquals(d.getDeathCounter(), 3);
    }

    @Test
    void setActiveFrenzy() {
        DamageBox d = new DamageBox();
        d.setActiveFrenzy(true);
        assertEquals(d.isActiveFrenzy(), true);
    }

    @Test
    void setPickUp() {
        DamageBox d = new DamageBox();
        d.setPickUp(false);
        assertEquals(d.isPickUp(), false);
    }

    @Test
    void setShootUp() {
        DamageBox d = new DamageBox();
        d.setShootUp(false);
        assertEquals(d.isShootUp(), false);
    }

    @Test
    void setDamageTot() {
        DamageBox d = new DamageBox();
        d.setDamageTot(4);
        assertEquals(d.getDamageTot(), 4);
    }

    @Test
    void setMaxPointIndex() {
        DamageBox d = new DamageBox();
        d.setMaxPointIndex(4);
        assertEquals(d.getMaxPointIndex(), 4);
    }

    @Test
    void getMaxPointArray() {
        DamageBox d = new DamageBox();
        assertEquals(d.getMaxPointArray().length, 6);
        assertEquals(d.getMaxPointArray()[0], 8);
        assertEquals(d.getMaxPointArray()[1], 6);
        assertEquals(d.getMaxPointArray()[2], 4);
        assertEquals(d.getMaxPointArray()[3], 2);
        assertEquals(d.getMaxPointArray()[4], 1);
        assertEquals(d.getMaxPointArray()[5], 1);
    }

    @Test
    void getFinalFrenzyArray() {
        DamageBox d = new DamageBox();
        assertEquals(d.getFinalFrenzyArray().length, 4);
        assertEquals(d.getFinalFrenzyArray()[0], 2);
        assertEquals(d.getFinalFrenzyArray()[1], 1);
        assertEquals(d.getFinalFrenzyArray()[1], 1);
        assertEquals(d.getFinalFrenzyArray()[3], 1);
    }

    @Test
    void increaseDamage() {

    }
}