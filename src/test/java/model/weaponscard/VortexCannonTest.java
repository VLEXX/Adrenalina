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

class VortexCannonTest {

    @Test
    void firstAttackSP() throws RemoteException {
        //caso base
       VortexCannon vortexCannon = new VortexCannon();
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
        assertEquals(vortexCannon.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(vortexCannon.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(vortexCannon.firstAttack(player, spawnPoint, allPlay), MessageEnum.POSITION_NOT_VALID);
    }

    @Test
    void firstAttack() {
        //caso base
        VortexCannon vortexCannon = new VortexCannon();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position positionVortex = new Position();
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        playerBoard.setMarksBox(marksBox);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(2));
        positionToAttack.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);
        positionVortex.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        try{
            allPlay = new InitializeAllPlay();
        } catch(Exception e) {
        }
        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(vortexCannon.firstAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso non vedo il vortex
        myPosition.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        assertEquals(vortexCannon.firstAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.POSITION_UNREACHABLE);

        //caso player non nel vortex
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        assertEquals(vortexCannon.firstAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso player troppo distante dal vortice
        positionToAttack.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        assertEquals(vortexCannon.firstAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.PLAYER_NOT_FOUND);

        //secondo attacco
        playerToAttack.remove(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        playerToAttack.add(Player.GREEN);
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState3 = new CurrentPlayerState(playerToAttack.get(1));
        Position positionToAttack2 = new Position();
        Position positionToAttack3 = new Position();
        PlayerBoard playerBoard2 = new PlayerBoard();
        PlayerBoard playerBoard3 = new PlayerBoard();
        MarksBox marksBox2 = new MarksBox();
        MarksBox marksBox3 = new MarksBox();
        marksBox2.setMyMarksMap(myPlayer, 1);
        marksBox3.setMyMarksMap(myPlayer, 1);
        playerBoard2.setMarksBox(marksBox2);
        playerBoard3.setMarksBox(marksBox3);
        positionToAttack2.setCurrentroom(map1.getRoomList().get(2));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        positionToAttack3.setCurrentroom(map1.getRoomList().get(2));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        positionToAttack3.getCurrentcell().addInCellPlayer(playerToAttack.get(1));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState3);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack3);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard3);
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso down
        positionToAttack2.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso left
        positionToAttack2.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(0));
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso up
        positionVortex.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso right
        positionVortex.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(3));
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.OK);

        //caso posizione sbagliata
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.PLAYER_NOT_FOUND);

        //caso giocatori non validi
        playerToAttack.remove(Player.GREEN);
        playerToAttack.add(Player.YELLOW);
        assertEquals(vortexCannon.secondAttack(myPlayer, playerToAttack, positionVortex, allPlay), MessageEnum.PLAYERS_NOT_VALID);
    }

    @Test
    void thirdAttack() {
        VortexCannon vortexCannon = new VortexCannon();
        Player player = null;
        InitializeAllPlay allPlay = null;
        ArrayList<Player> playerToAttack = null;
        assertEquals(vortexCannon.thirdAttack(player, playerToAttack, allPlay), MessageEnum.ATTACK_NOT_PRESENT);
    }
}