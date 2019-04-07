package Model;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CurrentDeckStateTest {

    @Test
    void getAmmodeck() {
        CurrentDeckState c = new CurrentDeckState();
        assertEquals(c.getAmmodeck(), null);
    }

    @Test
    void getWeaponsdeck() {
        CurrentDeckState c = new CurrentDeckState();
        assertEquals(c.getWeaponsdeck(), null);
    }

    @Test
    void getPowerupdeck() {
        CurrentDeckState c = new CurrentDeckState();
        assertEquals(c.getPowerupdeck(), null);
    }

    @Test
    void setAmmodeck() {
        CurrentDeckState c = new CurrentDeckState();
        Stack<Ammo> s = new Stack<>();
        Ammo a = new Ammo();
        int[] array = new int[]{1,1,1};
        a.setAmmoList(array);
        s.push(a);
        c.setAmmodeck(s);
        assertEquals(c.getAmmodeck().pop().getAmmoList(), array );
    }

    @Test
    void setWeaponsdeck() {
        CurrentDeckState c = new CurrentDeckState();
        Stack<Weapons> s = new Stack<>();
        s.push(Weapons.CYBERBLADE);
        c.setWeaponsdeck(s);
        assertEquals(c.getWeaponsdeck().pop(), Weapons.CYBERBLADE );
    }

    @Test
    void setPowerupdeck() {
        CurrentDeckState c = new CurrentDeckState();
        Stack<PowerUp> s = new Stack<>();
        s.push(PowerUp.NEWTON);
        c.setPowerupdeck(s);
        assertEquals(c.getPowerupdeck().pop(), PowerUp.NEWTON );
    }
}