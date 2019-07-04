/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.InitializeMap1;
import model.map.Map;
import model.map.Position;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.MarksBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlameThrowerTest {

    @Test
    void firstAttackSP() throws RemoteException {
        //caso base
        FlameThrower flameThrower = new FlameThrower();
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
        assertEquals(flameThrower.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(flameThrower.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(flameThrower.firstAttack(player, spawnPoint, allPlay), MessageEnum.POSITION_NOT_VALID);
    }

    @Test
    void firstAttack() {
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
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        marksBox2.setMyMarksMap(myPlayer, 1);
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
        assertEquals(flameThrower.firstAttack(myPlayer, playerToAttack, positionToAttack2, allPlay), MessageEnum.OK);

        //caso F move2
        positionToAttack.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        assertEquals(flameThrower.firstAttack(myPlayer, playerToAttack, positionToAttack2, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso F move
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(flameThrower.firstAttack(myPlayer, playerToAttack, positionToAttack, allPlay), MessageEnum.POSITION_NOT_VALID);

        //caso U
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        assertEquals(flameThrower.firstAttack(myPlayer, playerToAttack, positionToAttack2, allPlay), MessageEnum.OK);

        //caso L
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        assertEquals(flameThrower.firstAttack(myPlayer, playerToAttack, positionToAttack2, allPlay), MessageEnum.OK);
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