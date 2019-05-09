/**
 * @author Federico Scatà
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdatePacket implements Serializable {
    private int[] chart;
    private CurrentPlayerState currentPlayerState;
    private Map map;

    public UpdatePacket(int[] chart, CurrentPlayerState currentPlayerState, Map m){
        this.chart=chart;
        this.currentPlayerState=currentPlayerState;
        this.map=m;
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
