package model;

import model.datapacket.StatesEnum;
import model.gamedata.ChartScore;
import model.map.InitializeMap1;
import model.map.Map;
import model.map.StrategyMap;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.UpdatePacket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdatePacketTest {

    @Test
    void getCurrentPlayerState() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null);
        assertEquals(updatePacket.getCurrentPlayerState().getActiveplayer(), Player.YELLOW);
    }

    @Test
    void getChart() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null);
        assertEquals(updatePacket.getChart().getScore()[0], 0);
    }

    @Test
    void getMap() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null);
        assertEquals(updatePacket.getMap().getMapname(), "1");
    }

    @Test
    void getPositionHashMap() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, null);
        assertEquals(updatePacket.getPositionHashMap(), null);
    }

    @Test
    void getStatesEnum() {
        ChartScore chartScore = new ChartScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, null, StatesEnum.ACTION);
        assertEquals(updatePacket.getStatesEnum(), StatesEnum.ACTION);
    }
}