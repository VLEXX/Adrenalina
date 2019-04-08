//Author: Giulia Rivara
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageBoxTest {

    @Test
    void setMyDamageMap() {
        Player player = Player.BLACK;
        int damage = 3;
        DamageBox damageBox = new DamageBox();
        damageBox.setMyDamageMap(player, damage);
        assertEquals(damageBox.getMyDamageMap().size(), 1);
        assertEquals(damageBox.getMyDamageMap().get(player).intValue(), 3);
        damageBox.setMyDamageMap(player, 2);
        assertEquals(damageBox.getMyDamageMap().get(player).intValue(), 5);
    }

    @Test
    void setDeathCounter() {
        DamageBox d = new DamageBox();
        d.setDeathCounter(3);
        assertEquals(d.getDeathCounter(), 3);
    }

    @Test
    void setActiveFrenzy() {
        DamageBox d = new DamageBox();
        d.setActiveFrenzy(true);
        assertEquals(d.isActiveFrenzy(), true);
    }

    @Test
    void setPickUp() {
        DamageBox d = new DamageBox();
        d.setPickUp(false);
        assertEquals(d.isPickUp(), false);
    }

    @Test
    void setShootUp() {
        DamageBox d = new DamageBox();
        d.setShootUp(false);
        assertEquals(d.isShootUp(), false);
    }

    @Test
    void setDamageTot() {
        DamageBox d = new DamageBox();
        d.setDamageTot(4);
        assertEquals(d.getDamageTot(), 4);
    }

    @Test
    void setMaxPointIndex() {
        DamageBox d = new DamageBox();
        d.setMaxPointIndex(4);
        assertEquals(d.getMaxPointIndex(), 4);
    }

    //TODO
    @Test
    void getMaxPointArray() {

    }
    //TODO
    @Test
    void getFinalFrenzyArray() {

    }

    //TODO
    @Test
    void getFirstBlood() {

    }

    //TODO
    @Test
    void getMortalDamage() {

    }

    //TODO
    @Test
    void getRage(){

    }
}