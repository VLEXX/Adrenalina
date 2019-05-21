/**
 * @author Giulia Rivara & Federico Scatà
 */
package model.gamedata;

import model.datapacket.StatesEnum;
import servercontroller.ObserverUpdate;
import model.modelstates.State;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that initialize the player
 */
public class InitializeAllPlay {
    private ArrayList<CurrentTurnState> currentTurnState;
    private HashMap<Player, CurrentPlayerState> currentPlayerState;
    private CurrentDeckState currentDeckState;
    private ChartScore chartScore;
    private StateSelectedMap stateSelectedMap;
    private StateSelectedMode stateSelectedMode;
    private VoteMap voteMap;
    private VoteMode voteMode;
    private IDClientList idClientList;
    private HashMap<Player, State> playerState;
    private int playercounter;
    private int playercountertemp;
    private ArrayList<ObserverUpdate> observers;
    private boolean endgame;
    private State tempState;
    private Player[] SkullArray;
    private Player[] SecondSkullArray;


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
        idClientList = new IDClientList();
        currentDeckState = new CurrentDeckState();
        playerState = new HashMap<>();
        playercounter = 0;
        playercountertemp = 0;
        observers = new ArrayList<>();
        this.endgame=false;
        this.SkullArray= new Player[]{null,null,null,null,null,null,null,null};
        this.SecondSkullArray = new Player[]{null,null,null,null,null,null,null,null};

    }

    public State getTempState() {
        return tempState;
    }

    public void setTempState(State tempState) {
        this.tempState = tempState;
    }

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }

    public boolean isEndgame() {
        return endgame;
    }

    public synchronized void putInHashMapState(Player player, StatesEnum statesEnum, HashMap<StatesEnum, State> hashMap){
        this.playerState.put(player, hashMap.get(statesEnum));
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

    public IDClientList getIdClientList() {
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

    public synchronized State getPlayerState(Player player) {
        return playerState.get(player);
    }

    public synchronized HashMap<Player, State> getHashMapState() {
        return playerState;
    }

    public Player[] getSkullArray() {
        return SkullArray;
    }

    public Player[] getSecondSkullArray() {
        return SecondSkullArray;
    }
}
