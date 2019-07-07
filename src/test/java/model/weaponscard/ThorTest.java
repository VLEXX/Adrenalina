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

class ThorTest {

    @Test
    void firstAttackSP() throws RemoteException {
        //caso base
        Thor thor = new Thor();
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
        assertEquals(thor.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(thor.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(thor.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);
    }

    @Test
    void firstAttack() {
        //caso base
        Thor thor = new Thor();
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
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
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
        assertEquals(thor.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        assertEquals(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().getDamageTot(),3);

        //posizione player non visibile
        positionToAttack.setCurrentcell(map1.getRoomList().get(1).getCellsList().get(0));
        assertEquals(thor.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //secondo attacco
        playerToAttack.remove(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(0));
        Position positionToAttack2 = new Position();
        PlayerBoard playerBoard2 = new PlayerBoard();
        MarksBox marksBox2 = new MarksBox();
        marksBox2.setMyMarksMap(myPlayer, 1);
        playerBoard2.setMarksBox(marksBox2);
        positionToAttack2.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard2);
        assertEquals(thor.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        assertEquals(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().getDamageTot(),2);

        //caso 2 posizione sbagliata
        positionToAttack2.setCurrentcell(map1.getRoomList().get(4).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack2);
        assertEquals(thor.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //terzo attacco
        playerToAttack.remove(Player.YELLOW);
        playerToAttack.add(Player.BLUE);
        CurrentPlayerState attackCurrentPlayerState3 = new CurrentPlayerState(playerToAttack.get(0));
        Position positionToAttack3 = new Position();
        PlayerBoard playerBoard3 = new PlayerBoard();
        MarksBox marksBox3 = new MarksBox();
        marksBox3.setMyMarksMap(myPlayer, 1);
        playerBoard3.setMarksBox(marksBox2);
        positionToAttack3.setCurrentroom(map1.getRoomList().get(2));
        positionToAttack3.setCurrentcell(map1.getRoomList().get(2).getCellsList().get(1));
        positionToAttack3.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState3);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack3);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard3);
        assertEquals(thor.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);
        assertEquals(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().getDamageTot(),3);

        //caso posizione sbagliata
        positionToAttack3.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(thor.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.POSITION_NOT_FOUND);
    }
}