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
import model.playerdata.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SledgeHammerTest {

    @Test
    void firstAttackSP(){
        //caso base
        SledgeHammer sledgeHammer = new SledgeHammer();
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
        assertEquals(sledgeHammer.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(sledgeHammer.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(sledgeHammer.firstAttack(player, spawnPoint, allPlay), MessageEnum.POSITION_NOT_VALID);
    }

    @Test
    void firstAttack() {
        //caso base
        SledgeHammer sledgeHammer = new SledgeHammer();
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
        marksBox.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
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
        assertEquals(sledgeHammer.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //cella sbagliata
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(sledgeHammer.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_NOT_VALID);
    }

    @Test
    void secondAttack() {
        //caso base
        SledgeHammer sledgeHammer = new SledgeHammer();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToMove = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        DamageBox damageBox = new DamageBox();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        playerBoard.setMarksBox(marksBox);
        playerBoard.setDamageBox(damageBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(1));
        myPosition.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(1));
        positionToAttack.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso non stessa posizione
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.PLAYER_NOT_VALID);

        //caso direzione sbagliata
        positionToAttack.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_UNREACHABLE);

        //caso down 1 volta
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToMove.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso left 1 volta
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToMove.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso left 2 volte
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToMove.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso right 1 volta
        positionToAttack.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        positionToMove.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso right 2 volte
        positionToAttack.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso up 1 volta
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToMove.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso up 2 volte
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        myPosition.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToMove.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        assertEquals(sledgeHammer.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
    }

    @Test
    void thirdAttack() {
        SledgeHammer sledgeHammer = new SledgeHammer();
        Player myPlayer = null;
        ArrayList<Player> playerToAttack = null;
        InitializeAllPlay allPlay = null;
        assertEquals(sledgeHammer.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}