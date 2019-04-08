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
        powerupTest.add(PowerUp.TAGBACK_GRENADE);
        p.setPowerupList(powerupTest);
        assertEquals(p.getPowerupList().get(0), PowerUp.TAGBACK_GRENADE);
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

    //TODO
    @Test
    void getDamageBox(){

    }

    @Test
    void getMarksBox(){

    }

    @Test
    void getMunitionsBox(){

    }
}