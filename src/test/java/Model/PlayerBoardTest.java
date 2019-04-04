//Author: Giulia Rivara
package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBoardTest {

    private PlayerBoard p;
    @Test
    void getWeaponList() {
        p = new PlayerBoard();
        ArrayList<Weapons> weaponsTest = new ArrayList<>();
        assertEquals(p.getWeaponsList().size(), 0);
    }

    @Test
    void getFirtsPlayerCard() {
        p = new PlayerBoard();
        p.getFirtsPlayerCard();
        assertEquals(p.getFirtsPlayerCard(), false);
    }

    @Test
    void getPowerupList() {
        p = new PlayerBoard();
        ArrayList<PowerUp> powerupTest = new ArrayList<>();
        assertEquals(p.getWeaponsList().size(), 0);
    }

    @Test
    void setPowerupList() {
        p = new PlayerBoard();
        ArrayList<PowerUp> powerupTest = new ArrayList<>();
        p.setPowerupList(powerupTest);
        assertEquals(p.getPowerupList().size(), 0);
    }

    @Test
    void setWeaponsList() {
        p = new PlayerBoard();
        ArrayList<Weapons> weaponsTest = new ArrayList<>();
        p.setWeaponsList(weaponsTest);
        assertEquals(p.getWeaponsList().size(), 0);
    }

    @Test
    void setFirstPlayerCard() {
        p = new PlayerBoard();
        if(p.setFirstPlayerCard(false)){
            assertEquals(p.setFirstPlayerCard(false), false );
        }
        assertEquals(p.setFirstPlayerCard(true), true);
    }
}