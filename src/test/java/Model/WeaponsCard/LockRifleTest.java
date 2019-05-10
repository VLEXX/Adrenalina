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
    void firstAttack() {
        Position myposition = new Position();
        Player activeplayer = Player.BLUE;
        Position positionToAttack = new Position();
        PlayerBoard playerToAttack = new PlayerBoard();
        playerToAttack.setPlayer(Player.YELLOW);
        Map map1 = new Map();
        InitializeMap1 initializeMap1 = new InitializeMap1();
        map1 = initializeMap1.initializeMap();
        myposition.setCurrentroom(map1.getRoomList().get(0));
        myposition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.getPlayer());
        } catch (PlayerAlreadyAdded a) {
        }
        playerToAttack.setMarksBox(new MarksBox());
        playerToAttack.setDamageBox(new DamageBox());
        LockRifle lockRifle = new LockRifle();
        //Caso corretto
        try {
            playerToAttack = lockRifle.firstAttack(myposition, activeplayer, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
        assertEquals(playerToAttack.getDamageBox().getDamageTot(), 2);
        assertEquals(playerToAttack.getMarksBox().getMyMarksMap().get(Player.BLUE), 1);
        //Caso giocatore colore sbagliato
        try {
            positionToAttack.getCurrentcell().removeInCellPlayer(Player.YELLOW);
            positionToAttack.getCurrentcell().addInCellPlayer(Player.GREEN);
        } catch (PlayerNotFound p2) {
        } catch (PlayerAlreadyAdded p3) {
        }
        try {
            playerToAttack = lockRifle.firstAttack(myposition, activeplayer, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
        //Caso posizione sbagliata
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(Player.YELLOW);
        } catch (PlayerAlreadyAdded p5) {
        }
        try {
            playerToAttack = lockRifle.firstAttack(myposition, activeplayer, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
    }

    @Test
    void secondAttack() {
        Position myposition = new Position();
        Player activeplayer = Player.BLUE;
        Position positionToAttack = new Position();
        PlayerBoard playerToAttack = new PlayerBoard();
        playerToAttack.setPlayer(Player.YELLOW);
        Map map1 = new Map();
        InitializeMap1 initializeMap1 = new InitializeMap1();
        map1 = initializeMap1.initializeMap();
        myposition.setCurrentroom(map1.getRoomList().get(0));
        myposition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.getPlayer());
        } catch (PlayerAlreadyAdded a) {
        }
        playerToAttack.setMarksBox(new MarksBox());
        LockRifle lockRifle = new LockRifle();
        try {
            playerToAttack = lockRifle.secondAttack(activeplayer, myposition, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
        assertEquals(playerToAttack.getMarksBox().getMyMarksMap().get(Player.BLUE), 1);
    }
}
