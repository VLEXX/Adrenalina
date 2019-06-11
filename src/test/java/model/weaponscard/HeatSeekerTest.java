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

class HeatSeekerTest {

    @Test
    void firstAttack() {
        //caso corretto
        HeatSeeker heatSeeker = new HeatSeeker();
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
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
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
        assertEquals(heatSeeker.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);

        //caso position visibile
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(heatSeeker.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //flag
        //TODO

        //giocatore nullo
        playerToAttack.remove(Player.PURPLE);
        assertEquals(heatSeeker.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void secondAttack() {
        HeatSeeker heatSeeker = new HeatSeeker();
        Player myPlayer = Player.YELLOW;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        InitializeAllPlay allPlay = null;
        Position positionToMove = null;
        assertEquals(heatSeeker.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }

    @Test
    void thirdAttack() {
        HeatSeeker heatSeeker = new HeatSeeker();
        Player myPlayer = Player.YELLOW;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        InitializeAllPlay allPlay = null;
        assertEquals(heatSeeker.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}