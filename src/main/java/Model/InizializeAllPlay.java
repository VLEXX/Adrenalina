//Author: Giulia Rivara
package Model;

import java.util.ArrayList;

//Classe che inizializza la partita
public class InizializeAllPlay {
    private ArrayList<CurrentTurnState> currentTurnState;
    private ArrayList<CurrentPlayerState> currentPlayerState;
    private ArrayList<ChartScore> chartScore;
    private StateSelectedMap stateSelectedMap;
    private StateSelectedMode stateSelectedMode;
    private VoteMap voteMap;
    private VoteMode voteMode;
    private ArrayList<IDClientList> idClientList;

    public InizializeAllPlay(){
        currentTurnState = new ArrayList<>();
        currentPlayerState = new ArrayList<>();
        chartScore = new ArrayList<>();
        stateSelectedMode = new StateSelectedMode();
        stateSelectedMap = new StateSelectedMap();
        voteMap = new VoteMap();
        voteMode = new VoteMode();
        idClientList = new ArrayList<>();
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
}
