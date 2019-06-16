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

class ZX2Test {

    @Test
    void firstAttack() {
        //caso base
        ZX2 zx2 = new ZX2();
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
        marksBox.setMyMarksMap(myPlayer, 2);
        playerBoard.setMarksBox(marksBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
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
        assertEquals(zx2.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso giocatore non visibile
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(zx2.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);
    }

    @Test
    void secondAttack() {
        //caso base
        ZX2 zx2 = new ZX2();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        playerToAttack.add(Player.GREEN);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState1 = new CurrentPlayerState(playerToAttack.get(1));
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(2));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToAttack1 = new Position();
        Position positionToAttack2 = new Position();
        Position positionToMove = null;
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard1 = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox1 = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        marksBox1.setMyMarksMap(myPlayer, 2);
        marksBox2.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        playerBoard1.setMarksBox(marksBox1);
        playerBoard2.setMarksBox(marksBox2);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionToAttack1.setCurrentroom(map1.getRoomList().get(2));
        positionToAttack1.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        positionToAttack2.setCurrentroom(map1.getRoomList().get(2));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        positionToAttack1.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(2));
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState1);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(2), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack1);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard1);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).setBoard(playerBoard2);
        assertEquals(zx2.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso posizione non raggiungibile
        positionToAttack1.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(zx2.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso giocatore 1 non visibile
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(zx2.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso giocatore 2 non visibile
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack1.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        assertEquals(zx2.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso giocatore 3 non visibile
        positionToAttack2.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToAttack1.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        assertEquals(zx2.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso giocatori uguali
        playerToAttack.remove(Player.GREEN);
        playerToAttack.add(Player.YELLOW);
        assertEquals(zx2.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYERS_NOT_VALID);
    }

    @Test
    void thirdAttack() {
        ZX2 zx2 = new ZX2();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        myPlayer = Player.YELLOW;
        assertEquals(zx2.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}