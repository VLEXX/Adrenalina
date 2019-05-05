//Author: Giulia Rivara
package Model;

import java.util.ArrayList;
import java.util.HashMap;

//Classe che inizializza la partita
public class InitializeAllPlay {
    private ArrayList<CurrentTurnState> currentTurnState;
    private ArrayList<CurrentPlayerState> currentPlayerState;
    private CurrentDeckState currentDeckState;
    private ArrayList<ChartScore> chartScore;
    private StateSelectedMap stateSelectedMap;
    private StateSelectedMode stateSelectedMode;
    private VoteMap voteMap;
    private VoteMode voteMode;
    private ArrayList<IDClientList> idClientList;
    private HashMap<Player, State>  playerState;
    private int playercounter;
    private int playercountertemp;

    public InitializeAllPlay(){
        currentTurnState = new ArrayList<>();
        currentPlayerState = new ArrayList<>();
        chartScore = new ArrayList<>();
        stateSelectedMode = new StateSelectedMode();
        stateSelectedMap = new StateSelectedMap();
        voteMap = new VoteMap();
        voteMode = new VoteMode();
        idClientList = new ArrayList<>();
        currentDeckState = new CurrentDeckState();
        playerState = new HashMap<>();
        playercounter = 0;
        playercountertemp = 0;
    }

    public void resetPlayerCounterTemp(){
        playercountertemp=playercounter;
    }

    public void decreasePlayerCounterTemp(){
        playercountertemp--;
    }

    public synchronized int getPlayercountertemp(){
        return playercountertemp;
    }

    public void addPlayerCounter(){
        playercounter++;
        playercountertemp++;
    }

    public int getPlayercounter(){
        return playercounter;
    }

    public ArrayList<ChartScore> getChartScore() {
        return chartScore;
    }

    public VoteMap getVoteMap() {
        return voteMap;
    }

    public ArrayList<CurrentPlayerState> getCurrentPlayerState() {
        return currentPlayerState;
    }

    public ArrayList<CurrentTurnState> getCurrentTurnState() {
        return currentTurnState;
    }

    public StateSelectedMap getStateSelectedMap() {
        return stateSelectedMap;
    }

    public StateSelectedMode getStateSelectedMode() {
        return stateSelectedMode;
    }

    public VoteMode getVoteMode() {
        return voteMode;
    }

    public ArrayList<IDClientList> getIdClientList() {
        return idClientList;
    }

    public CurrentDeckState getCurrentDeckState() {
        return currentDeckState;
    }

    public void addPlayerState(Player player, State state){
        playerState.put(player, state);
    }

    public void updatePlayerState(Player player, State state){
        playerState.replace(player, state);
    }

    public State getPlayerState(Player player){
        return playerState.get(player);
    }
}
