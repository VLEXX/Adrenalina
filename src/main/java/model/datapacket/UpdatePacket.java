/**
 * @author Federico Scatà
 */
package model.datapacket;

import model.map.Map;
import model.playerdata.CurrentPlayerState;

import java.io.Serializable;

public class UpdatePacket implements Serializable {
    private int[] chart;
    private CurrentPlayerState currentPlayerState;
    private Map map;

    public UpdatePacket(int[] chart, CurrentPlayerState currentPlayerState, Map m) {
        this.chart = chart;
        this.currentPlayerState = currentPlayerState;
        this.map = m;
    }

    public CurrentPlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public int[] getChart() {
        return chart;
    }

    public Map getMap() {
        return map;
    }
}
