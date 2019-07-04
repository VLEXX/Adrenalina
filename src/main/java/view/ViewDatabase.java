package view;

import model.gamedata.Mode;
import model.playerdata.Player;
import model.map.Position;
import model.powerups.PowerUp;

import java.util.HashMap;
import java.util.Stack;

public class ViewDatabase {
    private ViewCurrentPlayerState viewCurrentPlayerState;
    private ViewMapState viewMapState;
    private Mode selectedMode;
    private ViewChartScore viewChartScore;
    private Position viewPlayerPosition;
    private HashMap<Player, ViewState> viewState;
    private Player thisplayer;
    private Stack<PowerUp> currentDeckState;
    private boolean endgame;
    private int clientToken;
    private HashMap<Player, Position> positionHashMap;
    private Player[] skullArray;
    private Player[] secondSkullArray;
    private String nickname;
    private boolean attackinprogress;
    private boolean finalfrenzy;
    private HashMap<Player,Player[]> playersdamage;
    private HashMap<Player, HashMap<Player,Integer>> playersmarks;

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
        this.skullArray= new Player[]{null,null,null,null,null,null,null,null};
        this.secondSkullArray = new Player[]{null,null,null,null,null,null,null,null};
        this.attackinprogress = false;
        this.finalfrenzy=false;
        this.playersdamage=new HashMap<>();
        this.playersmarks=new HashMap<>();
        this.selectedMode=null;
    }

    public void setSelectedMode(Mode selectedMode) {
        this.selectedMode = selectedMode;
    }

    public Mode getSelectedMode() {
        return selectedMode;
    }

    public boolean isAttackinprogress() {
        return attackinprogress;
    }

    public HashMap<Player, HashMap<Player,Integer>> getPlayersmarks() {
        return playersmarks;
    }

    public HashMap<Player, Player[]> getPlayersdamage() {
        return playersdamage;
    }

    public boolean getFinalFrenzy(){return finalfrenzy;}

    public void setAttackinprogress(boolean attackinprogress) {
        this.attackinprogress = attackinprogress;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Player[] getSkullArray() {
        return skullArray;
    }

    public Player[] getSecondSkullArray() {
        return secondSkullArray;
    }

    public void setSkullArray(Player[] skullArray) {
        this.skullArray = skullArray;
    }

    public void setSecondSkullArray(Player[] secondSkullArray) {
        this.secondSkullArray = secondSkullArray;
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
