//Author: Giulia Rivara
package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBoardTest {

    @Test
    void getWeaponList() {
        PlayerBoard p = new PlayerBoard();
        ArrayList<Weapons> weaponsTest = new ArrayList<>();
        weaponsTest.add(Weapons.CYBERBLADE);
        p.setWeaponsList(weaponsTest);
        assertEquals(p.getWeaponsList().get(0), Weapons.CYBERBLADE);
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
        powerupTest.add(PowerUp.TAGBACK_GRENADE_BLUE);
        p.setPowerupList(powerupTest);
        assertEquals(p.getPowerupList().get(0), PowerUp.TAGBACK_GRENADE_BLUE);
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
        ArrayList<Weapons> weaponsTest = new ArrayList<>();
        p.setWeaponsList(weaponsTest);
        assertEquals(p.getWeaponsList().size(), 0);
    }

    @Test
    void setFirstPlayerCard() {
        PlayerBoard p = new PlayerBoard();
        if(p.setFirstPlayerCard(false)){
            assertEquals(p.setFirstPlayerCard(false), false );
        }
        assertEquals(p.setFirstPlayerCard(true), true);
    }

    @Test
    void setDamageBox(){
        DamageBox d = new DamageBox();
        d.setPickUp(true);
        PlayerBoard p = new PlayerBoard();
        p.setDamageBox(d);
        assertEquals(p.getDamageBox().isPickUp(), true);
    }

    @Test
    void setMarksBox(){
        MarksBox m = new MarksBox();
        m.setMyMarksMap(Player.BLACK, 2);
        PlayerBoard p = new PlayerBoard();
        p.setMarksBox(m);
        assertEquals(p.getMarksBox().getMyMarksMap().get(Player.BLACK).intValue(), 2);
    }

    @Test
    void setMunitionsBox(){
        MunitionsBox m = new MunitionsBox();
        m.setMyMunitionsMap(Player.BLACK, 4);
        PlayerBoard p = new PlayerBoard();
        p.setMunitionsBox(m);
        assertEquals(p.getMunitionsBox().getMyMunitionsMap().get(Player.BLACK).intValue(), 4);
    }
}