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

class MachineGunTest {

    @Test
    void firstAttackSP(){
        //caso base
        MachineGun machineGun = new MachineGun();
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
        assertEquals(machineGun.firstAttack(player, spawnPoint, allPlay), MessageEnum.OK);

        //caso non in mode domination
        allPlay.getStateSelectedMode().setSelectedmode(Mode.BASE);
        assertEquals(machineGun.firstAttack(player, spawnPoint, allPlay), MessageEnum.ATTACK_NOT_PRESENT);

        //caso posizione errata
        allPlay.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        myPosition.setCurrentcell(allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(1).getCellsList().get(0));
        allPlay.getCurrentPlayerState().get(player).setPlayerposition(myPosition);
        assertEquals(machineGun.firstAttack(player, spawnPoint, allPlay), MessageEnum.POSITION_NOT_VALID);
    }

    @Test
    void firstAttack() {
        //caso base
        MachineGun machineGun = new MachineGun();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        playerToAttack.add(Player.GREEN);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        CurrentPlayerState attackCurrentPlayerState2 = new CurrentPlayerState(playerToAttack.get(1));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        Position positionToAttack2 = new Position();
        Position positionToMove = null;
        PlayerBoard playerBoard = new PlayerBoard();
        PlayerBoard playerBoard2 = new PlayerBoard();
        DamageBox damageBox = new DamageBox();
        DamageBox damageBox2 = new DamageBox();
        MarksBox marksBox = new MarksBox();
        MarksBox marksBox2 = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 1);
        marksBox2.setMyMarksMap(myPlayer, 2);
        playerBoard.setMarksBox(marksBox);
        playerBoard2.setMarksBox(marksBox2);
        playerBoard.setDamageBox(damageBox);
        playerBoard2.setDamageBox(damageBox2);
        InitializeAllPlay allPlay = null;
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack2.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
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
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard2);
        assertEquals(machineGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

       //caso player 1 non visibile
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(machineGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso player 2 non visibile
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(0));
        assertEquals(machineGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //secondo attacco
        playerToAttack.remove(Player.GREEN);
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        assertEquals(machineGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso giocatore diverso
        playerToAttack.remove(Player.PURPLE);
        playerToAttack.add(Player.YELLOW);
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(2));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(machineGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.CANNOT_USE_THIS_EFFECT);

        //terzo attaccoe vari casi
        playerToAttack.add(Player.GREEN);
        positionToAttack2.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack2.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setBoard(playerBoard2);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);

        playerToAttack.remove(Player.YELLOW);
        playerToAttack.add(Player.YELLOW);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);

        playerToAttack.remove(Player.YELLOW);
        playerToAttack.add(Player.PURPLE);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.PLAYERS_NOT_VALID);

        playerToAttack.remove(Player.GREEN);
        playerToAttack.add(Player.GREEN);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.PLAYERS_NOT_VALID);


        //caso player attaccato false
        playerToAttack.remove(Player.PURPLE);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);
        assertEquals(machineGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);

        //caso giocatore nullo
        machineGun.firstAttack(myPlayer, playerToAttack, positionToMove, allPlay);
        assertEquals(machineGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.CANNOT_USE_THIS_EFFECT);

        playerToAttack.add(Player.GREEN);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.PLAYERS_NOT_VALID);

        playerToAttack.remove(Player.GREEN);
        playerToAttack.add(Player.YELLOW);
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.POSITION_NOT_FOUND);

        allPlay.getCurrentPlayerState().remove(playerToAttack.get(0));
        allPlay.getCurrentPlayerState().remove(playerToAttack.get(1));

        positionToAttack2.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack2.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(1), attackCurrentPlayerState2);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).setPlayerposition(positionToAttack2);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.POSITION_NOT_FOUND);

        //caso flag secondo e terzo attacco
        playerToAttack.remove(1);
        playerToAttack.remove(0);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.OK);
        assertEquals(machineGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.OK);
        playerToAttack.add(Player.FLAG);
        assertEquals(machineGun.thirdAttack(myPlayer, playerToAttack, allPlay), MessageEnum.WEAPON_ERROR);

        //caso posizione errata secondo attacco
        playerToAttack.remove(0);
        playerToAttack.add(Player.GREEN);
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(2));
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        assertEquals(machineGun.secondAttack(myPlayer, playerToAttack, positionToMove, allPlay), MessageEnum.POSITION_NOT_FOUND);
    }
}