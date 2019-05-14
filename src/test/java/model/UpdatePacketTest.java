package model;

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
        ChartScore chartScore1 = new ChartScore();
        int[] chartScore = chartScore1.getScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map);
        assertEquals(updatePacket.getCurrentPlayerState().getActiveplayer(), Player.YELLOW);
    }

    @Test
    void getChart() {
        ChartScore chartScore1 = new ChartScore();
        int[] chartScore = chartScore1.getScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map);
        assertEquals(updatePacket.getChart()[0], 0);
    }

    @Test
    void getMap() {
        ChartScore chartScore1 = new ChartScore();
        int[] chartScore = chartScore1.getScore();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        StrategyMap strategyMap = new InitializeMap1();
        Map map = strategyMap.initializeMap();
        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map);
        assertEquals(updatePacket.getMap().getMapname(), "1");
    }
}