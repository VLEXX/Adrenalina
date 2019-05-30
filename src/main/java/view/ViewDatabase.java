package view;

import model.playerdata.Player;
import model.map.Position;
import model.powerups.PowerUp;

import java.util.HashMap;
import java.util.Stack;

public class ViewDatabase {
    private ViewCurrentPlayerState viewCurrentPlayerState;
    private ViewMapState viewMapState;
    private ViewChartScore viewChartScore;
    private Position viewPlayerPosition;
    private HashMap<Player, ViewState> viewState;
    private Player thisplayer;
    private Stack<PowerUp> currentDeckState;
    private boolean endgame;
    private int clientToken;
    private HashMap<Player, Position> positionHashMap;

    public ViewDatabase() {
        this.viewCurrentPlayerState = new ViewCurrentPlayerState();
        this.viewMapState = new ViewMapState();
        this.viewChartScore = new ViewChartScore();
        this.viewPlayerPosition = null;
        this.viewState = new HashMap<>();
        this.thisplayer = null;
        this.currentDeckState = null;
        this.endgame=false;
        this.clientToken=0;
        this.positionHashMap = new HashMap<>();
    }

    public HashMap<Player, Position> getPositionHashMap() {
        return positionHashMap;
    }

    public void setPositionHashMap(HashMap<Player, Position> positionHashMap) {
        this.positionHashMap = positionHashMap;
    }

    public int getClientToken() {
        return clientToken;
    }

    public void setClientToken(int clientToken) {
        this.clientToken = clientToken;
    }

    public boolean isEndgame() {
        return endgame;
    }

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }

    public Stack<PowerUp> getCurrentDeckState() {
        return currentDeckState;
    }

    public void setCurrentDeckState(Stack<PowerUp> currentDeckState1) {
        this.currentDeckState=null;
        this.currentDeckState = currentDeckState1;
    }

    public HashMap<Player, ViewState> getViewState() {
        return viewState;
    }

    public ViewCurrentPlayerState getViewCurrentPlayerState() {
        return viewCurrentPlayerState;
    }

    public ViewMapState getViewMapState() {
        return viewMapState;
    }

    public ViewChartScore getViewChartScore() {
        return viewChartScore;
    }

    public void setViewCurrentPlayerState(ViewCurrentPlayerState viewCurrentPlayerState) {
        this.viewCurrentPlayerState = viewCurrentPlayerState;
    }

    public void setViewChartScore(ViewChartScore viewChartScore) {
        this.viewChartScore = viewChartScore;
    }

    public void setViewMapState(ViewMapState viewMapState) {
        this.viewMapState = viewMapState;
    }

    public Position getViewPlayerPosition() {
        return viewPlayerPosition;
    }


    public void setViewPlayerPosition(Position viewPlayerPosition) {
        this.viewPlayerPosition = viewPlayerPosition;
    }

    public Player getThisplayer() {
        return thisplayer;
    }

    public void setThisplayer(Player thisplayer) {
        this.thisplayer = thisplayer;
    }
}
