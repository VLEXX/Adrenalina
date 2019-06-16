/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.map.InitializeMap1;
import model.map.Map;
import model.map.Position;
import model.playerdata.CurrentPlayerState;
import model.playerdata.MarksBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShotGunTest {

    @Test
    void firstAttack() {
        //caso base
        ShotGun shotGun = new ShotGun();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        positionToMove.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
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
        assertEquals(shotGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso giocatore non nella mia cella
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(shotGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_NOT_VALID);

        //caso position up
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToMove.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(shotGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso position false
        myPosition.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        positionToAttack.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(shotGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);
    }

    @Test
    void secondAttack() {
        //caso base
        ShotGun shotGun = new ShotGun();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = null;
        PlayerBoard playerBoard = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
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
        assertEquals(shotGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso false
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(shotGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso left
        myPosition.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(shotGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void thirdAttack() {
        ShotGun shotGun = new ShotGun();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        myPlayer = Player.YELLOW;
        assertEquals(shotGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}