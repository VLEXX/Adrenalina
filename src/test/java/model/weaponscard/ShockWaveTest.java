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

class ShockWaveTest {

    @Test
    void firstAttack() {
        //caso base
        ShockWave shockWave = new ShockWave();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        playerToAttack.add(Player.GREEN);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(1));
        CurrentPlayerState attackCurrentPlayerState3 = new CurrentPlayerState(playerToAttack.get(2));
        Position positionToMove = null;
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToAttack2 = new Position();
        Position positionToAttack3 = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        PlayerBoard playerBoard3 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        MarksBox marksBox3 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        marksBox2.setMyMarksMap(myPlayer, 1);
        marksBox3.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        playerBoard2.setMarksBox(marksBox2);
        playerBoard3.setMarksBox(marksBox3);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(3));
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        positionToAttack2.setCurrentroom(map1.getRoomList().get(1));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        positionToAttack3.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToAttack3.getCurrentcell().addInCellPlayer(playerToAttack.get(2));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(2), attackCurrentPlayerState3);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).setPlayerposition(positionToAttack3);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).setBoard(playerBoard3);
        assertEquals(shockWave.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso giocatore 1 sbagliato
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(shockWave.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso giocatore 2 sbagliato
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(shockWave.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso giocatore sbagliato 3
        positionToAttack2.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(shockWave.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso right
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(shockWave.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void secondAttack() {
        //caso base
        ShockWave shockWave = new ShockWave();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        playerToAttack.add(Player.GREEN);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(1));
        CurrentPlayerState attackCurrentPlayerState3 = new CurrentPlayerState(playerToAttack.get(2));
        Position positionToMove = null;
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToAttack2 = new Position();
        Position positionToAttack3 = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        PlayerBoard playerBoard3 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        MarksBox marksBox3 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        marksBox2.setMyMarksMap(myPlayer, 1);
        marksBox3.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        playerBoard2.setMarksBox(marksBox2);
        playerBoard3.setMarksBox(marksBox3);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(3));
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        positionToAttack2.setCurrentroom(map1.getRoomList().get(1));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        positionToAttack3.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToAttack3.getCurrentcell().addInCellPlayer(playerToAttack.get(2));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(2), attackCurrentPlayerState3);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).setPlayerposition(positionToAttack3);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).setBoard(playerBoard3);
        assertEquals(shockWave.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso right
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(shockWave.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        //TODO controllare
    }

    @Test
    void thirdAttack() {
        ShockWave shockWave = new ShockWave();
        Player player = null;
        InitializeAllPlay allPlay = null;
        ArrayList<Player> playerToAttack = null;
        assertEquals(shockWave.thirdAttack(player, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}