package model.gamedata;

import model.gamedata.CurrentDeckState;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(c.getPowerupdeck().empty(), false);
    }

    @Test
    void getPlayers() {
        CurrentDeckState c = new CurrentDeckState();
        assertEquals(c.getPlayers().contains(Player.YELLOW), true);
        assertEquals(c.getPlayers().contains(Player.PURPLE), true);
        assertEquals(c.getPlayers().contains(Player.GREEN), true);
        assertEquals(c.getPlayers().contains(Player.BLUE), true);
        assertEquals(c.getPlayers().contains(Player.BLACK), true);
    }
}