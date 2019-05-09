package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdatePacket implements Serializable {
    private ArrayList<ChartScore> chart;
    private CurrentPlayerState currentPlayerState;
    private Map map;

    public UpdatePacket(ArrayList<ChartScore> chart, CurrentPlayerState currentPlayerState, Map m){
        this.chart=chart;
        this.currentPlayerState=currentPlayerState;
        this.map=m;
    }
}
