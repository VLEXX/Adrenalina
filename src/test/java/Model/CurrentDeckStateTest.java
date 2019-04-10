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

}