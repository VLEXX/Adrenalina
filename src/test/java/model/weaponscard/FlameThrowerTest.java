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

class FlameThrowerTest {

    @Test
    void firstAttack() {
        FlameThrower flameThrower = new FlameThrower();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(1));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToAttack2 = new Position();
        Position positionToMove = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        marksBox2.setMyMarksMap(myPlayer, 3);
        playerBoard.setMarksBox(marksBox);
        playerBoard2.setMarksBox(marksBox2);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        positionToAttack2.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionToMove = positionToAttack2;
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(flameThrower.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        //finire TODO
    }

    @Test
    void secondAttack() {
        //caso base
        FlameThrower flameThrower = new FlameThrower();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(1));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToAttack2 = new Position();
        Position positionToMove = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        marksBox2.setMyMarksMap(myPlayer, 3);
        playerBoard.setMarksBox(marksBox);
        playerBoard2.setMarksBox(marksBox2);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        positionToAttack2.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionToMove = positionToAttack2;
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso F
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso T down
        positionToMove.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso up
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToMove = positionToAttack2;
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso down
        myPosition.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToMove = positionToAttack2;
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso left
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToMove = positionToAttack2;
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso up 1 cella
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        playerToAttack.remove(Player.YELLOW);
        myPosition.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        positionToMove = positionToAttack;
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso left 1 cella
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToMove = positionToAttack;
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso right 1 cella
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        myPosition.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        positionToMove = positionToAttack;
        assertEquals(flameThrower.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void thirdAttack() {
        FlameThrower flameThrower = new FlameThrower();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        assertEquals(flameThrower.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}