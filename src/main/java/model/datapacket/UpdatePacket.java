/**
 * @author Federico Scatà
 */
package model.datapacket;

import model.gamedata.ChartScore;
import model.map.Map;
import model.map.Position;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;

import java.io.Serializable;
import java.util.HashMap;

public class UpdatePacket implements Serializable {
    private ChartScore chartScore;
    private CurrentPlayerState currentPlayerState;
    private Map map;
    private HashMap<Player, Position> positionHashMap;
    private StatesEnum statesEnum;


    public UpdatePacket(ChartScore chart, CurrentPlayerState currentPlayerState, Map m, HashMap<Player, Position> hashMap, StatesEnum state) {
        this.chartScore = chart;
        this.currentPlayerState = currentPlayerState;
        this.map = m;
        this.positionHashMap = hashMap;
        this.statesEnum = state;
    }

    public CurrentPlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public ChartScore getChart() {
        return chartScore;
    }

    public Map getMap() {
        return map;
    }

    public HashMap<Player, Position> getPositionHashMap() {
        return positionHashMap;
    }

    public StatesEnum getStatesEnum() {
        return statesEnum;
    }


}
