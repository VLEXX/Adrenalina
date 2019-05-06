/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;
import Model.Exceptions.PlayerAlreadyAdded;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockRifleTest {

    @Test
    void attack() {
        Position myposition = new Position();
        Player activeplayer = Player.BLUE;
        Position positionToAttack = new Position();
        PlayerBoard playerToAttack = new PlayerBoard();
        Map map1 = new Map();
        InitializeMap1 initializeMap1 = new InitializeMap1();
        map1 = initializeMap1.initializeMap();
        myposition.setCurrentroom(map1.getRoomList().get(0));
        myposition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.getPlayer());
        }
        catch(PlayerAlreadyAdded a){}
        playerToAttack.setMarksBox(new MarksBox());
        playerToAttack.setDamageBox(new DamageBox());
        LockRifle lockRifle = new LockRifle();
        try {
            playerToAttack = lockRifle.attack(myposition, activeplayer, positionToAttack, playerToAttack);
        }
        catch(PlayerNotFound p){System.out.println("Errore 1");}
        catch(PositionNotFound ps){System.out.println("Errore 2");}
        assertEquals(playerToAttack.getDamageBox().getDamageTot(), 2);
        assertEquals(playerToAttack.getMarksBox().getMyMarksMap().get(Player.BLUE).intValue(), 1);
    }

    @Test
    void hooking() {
    }

    @Test
    void isFirstUse() {
    }

    @Test
    void getMunitionsToPay() {
    }

    @Test
    void getPriceToPay() {
    }
}