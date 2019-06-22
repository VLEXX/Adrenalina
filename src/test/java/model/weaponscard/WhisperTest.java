/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.map.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.MarksBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;

import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WhisperTest {

    @Test
    void firstAttack() {
        //caso base
        Whisper whisper = new Whisper();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.YELLOW);
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
        InitializeMap4 initializeMap4 = new InitializeMap4();
        Map map1 = initializeMap1.initializeMap();
        Map map4 = initializeMap4.initializeMap();
        myPosition.setCurrentroom(map4.getRoomList().get(2));
        myPosition.setCurrentcell(map4.getRoomList().get(2).getCellsList().get(0));
        positionToAttack.setCurrentroom(map4.getRoomList().get(4));
        positionToAttack.setCurrentcell(map4.getRoomList().get(4).getCellsList().get(2));
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
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso check false
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);

        //caso giocatore troppo vicino
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_TOO_MUCH_NEAR);

        //caso up
        myPosition.setCurrentcell(map4.getRoomList().get(5).getCellsList().get(0));
        positionToAttack.setCurrentcell(map4.getRoomList().get(0).getCellsList().get(0));
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //TODO sistemare
        //caso down
        myPosition.setCurrentcell(map4.getRoomList().get(1).getCellsList().get(1));
        positionToAttack.setCurrentcell(map4.getRoomList().get(4).getCellsList().get(2));
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso left
        myPosition.setCurrentcell(map4.getRoomList().get(4).getCellsList().get(2));
        positionToAttack.setCurrentcell(map4.getRoomList().get(5).getCellsList().get(0));
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso control != 0
        marksBox.setMyMarksMap(myPlayer, 1);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().setMarksBox(marksBox);
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void secondAttack() {
        Whisper whisper = new Whisper();
        Player myPlayer = null;
        Position positionToMove = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        assertEquals(whisper.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }

    @Test
    void thirdAttack() {
        Whisper whisper = new Whisper();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        assertEquals(whisper.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}