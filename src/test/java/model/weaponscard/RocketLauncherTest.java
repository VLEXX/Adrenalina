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

class RocketLauncherTest {

    @Test
    void firstAttackSP() {
    }

    @Test
    void firstAttack() {
        //caso base
        RocketLauncher rocketLauncher = new RocketLauncher();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.YELLOW);
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
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionToMove.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(rocketLauncher.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso player non visibile
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(rocketLauncher.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_UNREACHABLE);

        //caso stessa cella
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        assertEquals(rocketLauncher.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso mossa errata
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(rocketLauncher.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);

        //terzo attacco
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(1));
        Position positionToAttack2 = new Position();
        PlayerBoard playerBoard2 = new PlayerBoard();
        MarksBox marksBox2 = new MarksBox();
        marksBox2.setMyMarksMap(myPlayer, 1);
        playerBoard2.setMarksBox(marksBox2);
        positionToAttack2.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        assertEquals(rocketLauncher.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);
    }

    @Test
    void secondAttack() {
        //caso base
        RocketLauncher rocketLauncher = new RocketLauncher();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = null;
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        Position myPosition = new Position();
        Position positionToMove = new Position();
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionToMove.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        assertEquals(rocketLauncher.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso più di due quadrati/TODO check
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        assertEquals(rocketLauncher.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);
    }
}