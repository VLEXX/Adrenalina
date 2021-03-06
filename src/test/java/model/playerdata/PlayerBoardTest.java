/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.munitions.Munitions;
import model.powerups.PowerUp;
import model.powerups.TagbackGrenade;
import model.weaponscard.LockRifle;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerBoardTest {

    @Test
    void getPlayer() {
        PlayerBoard p = new PlayerBoard();
        p.setPlayer(Player.YELLOW);
        assertEquals(p.getPlayer(), Player.YELLOW);
    }

    @Test
    void getWeaponList() {
        PlayerBoard p = new PlayerBoard();
        ArrayList<Weapon> weaponsTest = new ArrayList<>();
        LockRifle lockRifle = new LockRifle();
        weaponsTest.add(lockRifle);
        p.setWeaponsList(weaponsTest);
        assertEquals(p.getWeaponsList().get(0), lockRifle);
    }

    @Test
    void getFirtsPlayerCard() {
        PlayerBoard p = new PlayerBoard();
        p.getFirtsPlayerCard();
        assertEquals(p.getFirtsPlayerCard(), false);
    }

    @Test
    void getPowerupList() {
        PlayerBoard p = new PlayerBoard();
        ArrayList<PowerUp> powerupTest = new ArrayList<>();
        PowerUp powerUp = new TagbackGrenade(Munitions.BLUE);
        powerupTest.add(powerUp);
        p.setPowerupList(powerupTest);
        assertEquals(p.getPowerupList().get(0), powerUp);
    }

    @Test
    void setPowerupList() {
        PlayerBoard p = new PlayerBoard();
        ArrayList<PowerUp> powerupTest = new ArrayList<>();
        p.setPowerupList(powerupTest);
        assertEquals(p.getPowerupList().size(), 0);
    }

    @Test
    void setWeaponsList() {
        PlayerBoard p = new PlayerBoard();
        ArrayList<Weapon> weaponsTest = new ArrayList<>();
        p.setWeaponsList(weaponsTest);
        assertEquals(p.getWeaponsList().size(), 0);
    }

    @Test
    void setFirstPlayerCard() {
        PlayerBoard p = new PlayerBoard();
        if (p.setFirstPlayerCard(false)) {
            assertEquals(p.setFirstPlayerCard(false), false);
        }
        assertEquals(p.setFirstPlayerCard(true), true);
    }

    @Test
    void setDamageBox() {
        DamageBox d = new DamageBox();
        d.setPickUp(true);
        PlayerBoard p = new PlayerBoard();
        p.setDamageBox(d);
        assertEquals(p.getDamageBox().isPickUp(), true);
    }

    @Test
    void setMarksBox() {
        MarksBox m = new MarksBox();
        m.setMyMarksMap(Player.BLACK, 2);
        PlayerBoard p = new PlayerBoard();
        p.setMarksBox(m);
        assertEquals(p.getMarksBox().getMyMarksMap().get(Player.BLACK).intValue(), 2);
    }

    @Test
    void setMunitionsBox() {
        MunitionsBox munitionsBox = new MunitionsBox();
        PlayerBoard p = new PlayerBoard();
        munitionsBox.increaseMyMunitionsBox(Munitions.BLUE, 2);
        p.setMunitionsBox(munitionsBox);
        assertEquals(p.getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE), 5);
    }
}