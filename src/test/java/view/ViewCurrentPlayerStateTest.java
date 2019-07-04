package view;

import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.weaponscard.LockRifle;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewCurrentPlayerStateTest {

    @Test
    void getCurrentPlayerState() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        assertEquals(v.getCurrentPlayerState(), null);
    }

    @Test
    void setCurrentPlayerState() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        CurrentPlayerState c = new CurrentPlayerState(Player.BLACK);
        c.setActiveplayer(Player.BLACK);
        v.setCurrentPlayerState(c);
        assertEquals(v.getCurrentPlayerState().getActiveplayer(), Player.BLACK);
    }

    @Test
    void setWeaponMultAttacks() {
        ViewCurrentPlayerState v = new ViewCurrentPlayerState();
        Weapon weapon = new LockRifle();
        v.setWeaponMultAttacks(weapon);
        assertEquals(v.getWeaponMultAttacks(), weapon);
    }
}