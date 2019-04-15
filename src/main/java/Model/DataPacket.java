package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataPacket implements Serializable {

    private Map<Player, CurrentPlayerState> playerstatemap;
    private Model.Map datamap;
    private ChartScore datachart;

    public DataPacket(Player player1, Player player2, Player player3){
        playerstatemap = new HashMap<>();
        datamap = new Model.Map();
        datachart = new ChartScore();
        playerstatemap.put(player1, null);
        playerstatemap.put(player2, null);
        playerstatemap.put(player3, null);
        datamap = null;
        datachart = null;
    }

    public DataPacket(Player player1, Player player2, Player player3, Player player4){
        playerstatemap = new HashMap<>();
        datamap = new Model.Map();
        datachart = new ChartScore();
        playerstatemap.put(player1, null);
        playerstatemap.put(player2, null);
        playerstatemap.put(player3, null);
        playerstatemap.put(player4, null);
        datamap = null;
        datachart = null;
    }

    public DataPacket(Player player1, Player player2, Player player3, Player player4, Player player5){
        playerstatemap = new HashMap<>();
        datamap = new Model.Map();
        datachart = new ChartScore();
        playerstatemap.put(player1, null);
        playerstatemap.put(player2, null);
        playerstatemap.put(player3, null);
        playerstatemap.put(player4, null);
        playerstatemap.put(player5, null);
        datamap = null;
        datachart = null;
    }

    public Map<Player, CurrentPlayerState> getPlayerstatemap() {
        return playerstatemap;
    }

    public Model.Map getDatamap() {
        return datamap;
    }

    public ChartScore getDatachart() {
        return datachart;
    }

    public void updatePlayerstate(Player player, CurrentPlayerState currentplayerstate) {
        playerstatemap.replace(player, currentplayerstate);
    }

    public void setDatamap(Model.Map datamap) {
        this.datamap = datamap;
    }

    public void setDatachart(ChartScore datachart) {
        this.datachart = datachart;
    }

}
