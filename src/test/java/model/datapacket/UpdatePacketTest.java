package model.datapacket;

import model.gamedata.ChartScore;
import model.gamedata.Mode;
import model.map.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.PowerUp;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdatePacketTest {

    @Test
    void getCurrentPlayerState() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null, null, false);
        assertEquals(updatePacket.getCurrentPlayerState().getActiveplayer(), Player.YELLOW);
    }

    @Test
    void getChart() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null, null, false);
        assertEquals(updatePacket.getChart().getScore()[0], 0);
    }

    @Test
    void getMap() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null, null, false);
        assertEquals(updatePacket.getMap().getMapname(), "1");
    }

    @Test
    void getPositionHashMap() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null, null, false);
        assertEquals(updatePacket.getPosition(), null);
    }

    @Test
    void getStatesEnum() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, StatesEnum.ACTION, null, false);
        assertEquals(updatePacket.getStatesEnum(), StatesEnum.ACTION);
    }

    @Test
    void isEndgame() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        Stack<PowerUp> stack = new Stack<>();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, StatesEnum.ACTION, stack, false);
        assertEquals(updatePacket.isEndgame(), false);
        assertEquals(updatePacket.getPowerUpDeck(), stack);
    }

    @Test
    void addInHashMap() {
        UpdatePacket updatePacket = new UpdatePacket(null,null,null,null,null,null, false);
        Position position = new Position();
        Room room = new Room(1);
        position.setCurrentroom(room);
        updatePacket.addInHashMap(Player.YELLOW,position);
        assertEquals(updatePacket.getPositionHashMap().get(Player.YELLOW).getCurrentroom().getRoomId(), 1);
    }

    @Test
    void setSkullArray() {
        UpdatePacket updatePacket = new UpdatePacket(null, null, null, null, null, null, false);
        Player[] skull = {Player.BLUE,null,null,null,null,null,null,null};
        updatePacket.setSkullArray(skull);
        assertEquals(updatePacket.getSkullArray()[0], Player.BLUE);
    }

    @Test
    void setSecondSkullArray() {
        UpdatePacket updatePacket = new UpdatePacket(null, null, null, null, null, null, false);
        Player[] skull = {Player.BLUE,null,null,null,null,null,null,null};
        updatePacket.setSecondSkullArray(skull);
        assertEquals(updatePacket.getSecondSkullArray()[0], Player.BLUE);
    }

    @Test
    void setAttackinprogress() {
        UpdatePacket updatePacket = new UpdatePacket(null, null, null, null, null, null, false);
        updatePacket.setAttackinprogress(true);
        assertEquals(updatePacket.isAttackinprogress(), true);
    }

    @Test
    void setSelectedMode() {
        UpdatePacket updatePacket = new UpdatePacket(null, null, null, null, null, null, false);
        updatePacket.setSelectedMode(Mode.BASE);
        assertEquals(updatePacket.getSelectedMode(), Mode.BASE);
    }

    @Test
    void setDamage() {
        UpdatePacket updatePacket = new UpdatePacket(null, null, null, null, null, null, false);
        Player[] players = new Player[]{Player.YELLOW, Player.BLACK};
        updatePacket.setDamage(Player.BLUE, players);
        assertEquals(updatePacket.getDamage().get(Player.BLUE), players);
    }

    @Test
    void setMarks() {
        UpdatePacket updatePacket = new UpdatePacket(null, null, null, null, null, null, false);
        HashMap<Player, Integer> hashMap = new HashMap<>();
        hashMap.put(Player.BLUE, 1);
        updatePacket.setMarks(Player.BLUE, hashMap);
        assertEquals(updatePacket.getMarks().get(Player.BLUE), hashMap);
    }
}