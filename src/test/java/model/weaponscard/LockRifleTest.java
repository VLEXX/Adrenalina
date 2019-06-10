/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.InitializeMap1;
import model.map.Map;
import model.map.Position;
import model.playerdata.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LockRifleTest {

    @Test
    void firstAttack() {
        //caso generale
        LockRifle lockRifle = new LockRifle();
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
        assertEquals(lockRifle.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso check false
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(lockRifle.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso giocatore nullo
        playerToAttack.remove(Player.PURPLE);
        assertEquals(lockRifle.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void secondAttack() {
        //Inizializzazione generale
        LockRifle l = new LockRifle();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = null;
        Cell cell = new Cell(4);
        Cell cell2 = new Cell(6);
        myPosition.setCurrentcell(cell);
        positionToAttack.setCurrentcell(cell2);
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
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(l.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso check false
        positionToAttack.setCurrentroom(map1.getRoomList().get(4));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(l.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso empty playerToAttack
        playerToAttack.remove(0);
        allPlay.getCurrentPlayerState().get(playerToAttack);
        assertEquals(l.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso flag
        //TODO
    }

    @Test
    void thirdAttack(){
        LockRifle l = new LockRifle();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        assertEquals(l.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);
        myPlayer = Player.YELLOW;
        assertEquals(l.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}