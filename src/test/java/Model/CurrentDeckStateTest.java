package Model;

import Model.Powerups.Newton;
import Model.Powerups.TagbackGrenade;
import Model.Powerups.TargetingScope;
import Model.Powerups.Teleporter;
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
        assertEquals(c.getPowerupdeck().empty(), false);
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