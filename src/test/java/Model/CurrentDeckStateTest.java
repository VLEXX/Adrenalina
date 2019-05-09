package Model;

import Model.WeaponsCard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CurrentDeckStateTest {

    @Test
    void getAmmodeck() {
        CurrentDeckState c = new CurrentDeckState();
        assertEquals(c.getAmmodeck().isEmpty(), false);
    }

    @Test
    void getWeaponsdeck() {
        CurrentDeckState c = new CurrentDeckState();
        Stack<Weapon> deck = c.getWeaponsdeck();
        assertEquals(deck, c.getWeaponsdeck());
    }

    @Test
    void getPowerupdeck() {
        CurrentDeckState c = new CurrentDeckState();
        for(int i=0; i<2; i++) {
            for (PowerUp powerup : PowerUp.values()) {
                assertEquals(c.getPowerupdeck().contains(powerup), true);
            }
        }
    }

    @Test
    void getPlayers(){
        CurrentDeckState c = new CurrentDeckState();
        assertEquals(c.getPlayers().contains(Player.YELLOW), true);
        assertEquals(c.getPlayers().contains(Player.PURPLE), true);
        assertEquals(c.getPlayers().contains(Player.GREEN), true);
        assertEquals(c.getPlayers().contains(Player.BLUE), true);
        assertEquals(c.getPlayers().contains(Player.BLACK), true);
    }


}