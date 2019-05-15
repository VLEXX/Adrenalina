/**
 * @author Giulia Rivara & Federico Scatà
 */
package model.gamedata;

import servercontroller.ObserverUpdate;
import model.modelstates.State;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that initialize the player
 */
public class InitializeAllPlay implements SubjectUpdate {
    private ArrayList<CurrentTurnState> currentTurnState;
    private HashMap<Player, CurrentPlayerState> currentPlayerState;
    private CurrentDeckState currentDeckState;
    private ChartScore chartScore;
    private StateSelectedMap stateSelectedMap;
    private StateSelectedMode stateSelectedMode;
    private VoteMap voteMap;
    private VoteMode voteMode;
    private ArrayList<IDClientList> idClientList;
    private HashMap<Player, State> playerState;
    private int playercounter;
    private int playercountertemp;
    private ArrayList<ObserverUpdate> observers;


    /**
     * Constructor
     */
    public InitializeAllPlay() {
        currentTurnState = new ArrayList<>();
        currentPlayerState = new HashMap<>();
        chartScore = new ChartScore();
        stateSelectedMode = new StateSelectedMode();
        stateSelectedMap = new StateSelectedMap();
        voteMap = new VoteMap();
        voteMode = new VoteMode();
        idClientList = new ArrayList<>();
        currentDeckState = new CurrentDeckState();
        playerState = new HashMap<>();
        playercounter = 0;
        playercountertemp = 0;
        observers = new ArrayList<>();

    }

    public void resetPlayerCounterTemp() {
        playercountertemp = playercounter;
    }

    public void decreasePlayerCounterTemp() {
        playercountertemp--;
    }

    public synchronized int getPlayercountertemp() {
        return playercountertemp;
    }

    public void addPlayerCounter() {
        playercounter++;
        playercountertemp++;
    }

    public int getPlayercounter() {
        return playercounter;
    }

    public ChartScore getChartScore() {
        return chartScore;
    }

    public VoteMap getVoteMap() {
        return voteMap;
    }

    public HashMap<Player, CurrentPlayerState> getCurrentPlayerState() {
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

    public void addPlayerState(Player player, State state) {
        playerState.put(player, state);
    }

    public void updatePlayerState(Player player, State state) {
        playerState.replace(player, state);
    }

    public State getPlayerState(Player player) {
        return playerState.get(player);
    }

    public HashMap<Player, State> getHashMapState() {
        return playerState;
    }

    @Override
    public void addObserver(ObserverUpdate e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(ObserverUpdate e) {
        observers.remove(e);
    }

    @Override
    public void notifyObserver() {
        for (ObserverUpdate observer : this.getObservers()) {
            observer.update(true);
        }
    }

    @Override
    public ArrayList<ObserverUpdate> getObservers() {
        return observers;
    }
}
