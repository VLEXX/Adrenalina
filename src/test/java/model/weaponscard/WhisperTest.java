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

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WhisperTest {

    @Test
    void firstAttackSP() throws RemoteException {
        //caso base
        Whisper whisper = new Whisper();
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
        assertEquals(whisper.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(whisper.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(whisper.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);
    }

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
        positionToAttack.setCurrentcell(map4.getRoomList().get(0).getCellsList().get(1));
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_TOO_MUCH_NEAR);

        //caso down
        myPosition.setCurrentcell(map4.getRoomList().get(1).getCellsList().get(1));
        positionToAttack.setCurrentcell(map4.getRoomList().get(4).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_TOO_MUCH_NEAR);

        //caso left
        myPosition.setCurrentcell(map4.getRoomList().get(4).getCellsList().get(2));
        positionToAttack.setCurrentcell(map4.getRoomList().get(5).getCellsList().get(1));
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(whisper.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_TOO_MUCH_NEAR);

        //caso control !=0
        myPosition.setCurrentcell(map4.getRoomList().get(2).getCellsList().get(0));
        positionToAttack.setCurrentcell(map4.getRoomList().get(4).getCellsList().get(2));
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        marksBox.setMyMarksMap(myPlayer, 1);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
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