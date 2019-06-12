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
import model.playerdata.CurrentPlayerState;
import model.playerdata.MarksBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CyberbladeTest {

    @Test
    void firstAttack() {
        Cyberblade c = new Cyberblade();

        //creo i player, la loro current state e le loro position, nelle position inserisco la loro cella
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.YELLOW);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = null;
        Cell cell = new Cell(3);
        myPosition.setCurrentcell(cell);
        positionToAttack.setCurrentcell(cell);

        //creo la playerBoard per ogni giocatore(tranne myplayer), poi creo marksBox, la setto, e poi la metto dentro playerboard
        PlayerBoard playerBoard1 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        playerBoard1.setMarksBox(marksBox);

        //inizializzo allplay, inserendo nella getCurrentPlayerState tutti i giocatori
        InitializeAllPlay allPlay = null;
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer,myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0),attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard1);
        assertEquals(c.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        Cell cell2 = new Cell(4);
        positionToAttack.setCurrentcell(cell2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(c.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_NOT_VALID);
    }

    @Test
    void secondAttack() {
        Cyberblade c = new Cyberblade();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        Position myPosition = new Position();
        Position positionToMove = new Position();
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map = initializeMap1.initializeMap();
        myPosition.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        positionToMove.setCurrentcell(map.getRoomList().get(0).getCellsList().get(2));
        InitializeAllPlay allPlay = null;
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);
        positionToMove.setCurrentcell(map.getRoomList().get(0).getCellsList().get(1));
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //casi checkposition
        positionToMove.setCurrentcell(map.getRoomList().get(2).getCellsList().get(0));
        myPosition.setCurrentcell(map.getRoomList().get(2).getCellsList().get(1));
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        myPosition.setCurrentcell(map.getRoomList().get(2).getCellsList().get(0));
        positionToMove.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        myPosition.setCurrentcell(map.getRoomList().get(2).getCellsList().get(1));
        positionToMove.setCurrentcell(map.getRoomList().get(4).getCellsList().get(0));
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        myPosition.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        positionToMove.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        myPosition.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        positionToMove.setCurrentcell(map.getRoomList().get(0).getCellsList().get(1));
        assertEquals(c.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void thirdAttack() {
        //caso id cella diverso
        Cyberblade c = new Cyberblade();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        PlayerBoard playerBoard1 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        playerBoard1.setMarksBox(marksBox);InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map.getRoomList().get(0));
        positionToAttack.setCurrentroom(map.getRoomList().get(0));
        myPosition.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentcell(map.getRoomList().get(0).getCellsList().get(1));
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer,myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0),attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard1);
        assertEquals(c.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.PLAYER_NOT_VALID);

        //caso base
        positionToAttack.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(c.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);

        //giocatore uguale al precedente
        Position positionToMove = null;
        myPosition.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentcell(map.getRoomList().get(0).getCellsList().get(0));
        allPlay.getCurrentPlayerState().put(myPlayer,myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0),attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard1);
        assertEquals(c.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        assertEquals(c.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.PLAYER_NOT_VALID);
    }
}