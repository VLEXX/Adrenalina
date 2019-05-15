package view;

import model.playerdata.Player;
import model.map.Position;
import view.viewstates.ViewState;

import java.util.HashMap;

public class ViewDatabase {
    private ViewCurrentPlayerState viewCurrentPlayerState;
    private ViewMapState viewMapState;
    private ViewChartScore viewChartScore;
    private HashMap<Player, Position> viewPlayerPosition;
    private HashMap<Player, ViewState> viewState;

    public ViewDatabase(){
        this.viewCurrentPlayerState=null;
        this.viewMapState = null;
        this.viewChartScore = null;
        this.viewPlayerPosition = new HashMap<>();
        this.viewState = new HashMap<>();
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

    public HashMap<Player, Position> getViewPlayerPosition() {
        return viewPlayerPosition;
    }

    public void setViewPlayerPosition(HashMap<Player, Position> viewPlayerPosition) {
        this.viewPlayerPosition = viewPlayerPosition;
    }
}
