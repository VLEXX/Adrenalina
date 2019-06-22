/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.map.InitializeMap1;
import model.map.Map;
import model.map.Position;
import model.playerdata.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PowerGloveTest {

    @Test
    void firstAttack() {
        //caso base
        PowerGlove powerGlove = new PowerGlove();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = null;
        PlayerBoard playerBoard = new PlayerBoard();
        DamageBox damageBox = new DamageBox();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        playerBoard.setDamageBox(damageBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(1));
        myPosition.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(powerGlove.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso sbagliato
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(powerGlove.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);
    }

    @Test
    void secondAttack() {
        //caso base
        PowerGlove powerGlove = new PowerGlove();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = null;
        PlayerBoard playerBoard = new PlayerBoard();
        DamageBox damageBox = new DamageBox();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        playerBoard.setDamageBox(damageBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(1));
        myPosition.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(powerGlove.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso TODO finire
    }

    @Test
    void thirdAttack() {
        PowerGlove powerGlove = new PowerGlove();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        assertEquals(powerGlove.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}