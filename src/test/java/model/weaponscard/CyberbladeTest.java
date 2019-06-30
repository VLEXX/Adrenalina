/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.*;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.MarksBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CyberbladeTest {

    @Test
    void firstAttackSP(){
        //caso base
        Cyberblade cyberblade = new Cyberblade();
        Position myPosition = new Position();
        Player player = Player.YELLOW;
        Munitions munitions = Munitions.BLUE;
        SpawnPoint spawnPoint = new SpawnPoint(munitions);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(player);

        InitializeAllPlay allPlay = null;
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getStateSelectedMap().setStrategyMap(1);
        allPlay.getStateSelectedMap().setSelectedmap();
        myPosition.setCurrentroom(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        allPlay.getCurrentPlayerState().put(player, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setSpawnpointzone(spawnPoint);
        assertEquals(cyberblade.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(cyberblade.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(cyberblade.firstAttack(player, spawnPoint, allPlay), MessageEnum.POSITION_NOT_VALID);
    }

    @Test
    void firstAttack() {
        //caso base
        Cyberblade c = new Cyberblade();
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
        //caso base
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