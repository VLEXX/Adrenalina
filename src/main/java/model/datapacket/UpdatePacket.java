/**
 * @author Federico Scatà
 */
package model.datapacket;

import model.gamedata.ChartScore;
import model.map.Map;
import model.map.Position;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.PowerUp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Stack;

public class UpdatePacket implements Serializable {
    private ChartScore chartScore;
    private CurrentPlayerState currentPlayerState;
    private Map map;
    private Position position;
    private StatesEnum statesEnum;
    private Stack<PowerUp> powerUpDeck;
    private boolean endgame;
    private HashMap<Player, Position> positionHashMap;
    private Player[] SkullArray;
    private Player[] SecondSkullArray;


    public UpdatePacket(ChartScore chart, CurrentPlayerState currentPlayerState, Map m, Position position, StatesEnum state, Stack<PowerUp> deck, boolean end) {
        this.chartScore = chart;
        this.currentPlayerState = currentPlayerState;
        this.map = m;
        this.position = position;
        this.statesEnum = state;
        this.powerUpDeck = deck;
        this.endgame=end;
        this.positionHashMap = new HashMap<>();
        this.SkullArray = new Player[]{null, null, null, null, null, null, null, null};
        this.SecondSkullArray = new Player[]{null,null,null,null,null,null,null,null};
    }

    public Player[] getSkullArray() {
        return SkullArray;
    }

    public Player[] getSecondSkullArray() {
        return SecondSkullArray;
    }

    public void setSkullArray(Player[] skullArray) {
        SkullArray = skullArray;
    }

    public void setSecondSkullArray(Player[] secondSkullArray) {
        SecondSkullArray = secondSkullArray;
    }

    public HashMap<Player, Position> getPositionHashMap() {
        return positionHashMap;
    }

    public void addInHashMap(Player player, Position position){
        this.positionHashMap.put(player, position);
    }

    public boolean isEndgame() {
        return endgame;
    }

    public Stack<PowerUp> getPowerUpDeck() {
        return powerUpDeck;
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

    public Position getPosition() {
        return position;
    }

    public StatesEnum getStatesEnum() {
        return statesEnum;
    }


}
