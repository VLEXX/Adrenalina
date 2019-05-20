package view;

import model.gamedata.CurrentDeckState;
import model.playerdata.Player;
import model.map.Position;
import model.powerups.PowerUp;
import view.viewstates.ViewState;

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

    public ViewDatabase() {
        this.viewCurrentPlayerState = new ViewCurrentPlayerState();
        this.viewMapState = new ViewMapState();
        this.viewChartScore = new ViewChartScore();
        this.viewPlayerPosition = null;
        this.viewState = new HashMap<>();
        this.thisplayer = null;
        this.currentDeckState = null;
        this.endgame=false;
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

    public void setCurrentDeckState(Stack<PowerUp> currentDeckState) {
        this.currentDeckState = currentDeckState;
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
