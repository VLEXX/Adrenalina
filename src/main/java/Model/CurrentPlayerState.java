package Model;

public class CurrentPlayerState {
    private Position PlayerPosition;
    private boolean ActiveTurn;
    private PlayerBoard Board;
    private Action ActionState;
    private int ActionCounter;
    private Player ActivePlayer;

    public CurrentPlayerState(){
        this.PlayerPosition=null;
        this.ActiveTurn=false;
        this.Board=null;
        this.ActionState=null;
        this.ActionCounter=2;
        this.ActivePlayer=null;
    }

    public Position getPlayerPosition() {
        return this.PlayerPosition;
    }

    public void setPlayerPosition(Position playerPosition) {
        this.PlayerPosition = playerPosition;
    }

    public boolean isActiveTurn() {
        return this.ActiveTurn;
    }

    public void setActiveTurn(boolean activeTurn) {
        this.ActiveTurn = activeTurn;
    }

    public PlayerBoard getBoard() {
        return this.Board;
    }

    public void setBoard(PlayerBoard board) {
        this.Board = board;
    }

    public Action getActionState() {
        return this.ActionState;
    }

    public void setActionState(Action actionState) {
        this.ActionState = actionState;
    }

    public int getActionCounter() {
        return this.ActionCounter;
    }

    public void resetActionCounter() {
        this.ActionCounter = 2;
    }

    public void decreaseActionCounter() {
        this.ActionCounter = ActionCounter-1;
    }

    public Player getActivePlayer() {
        return this.ActivePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.ActivePlayer = activePlayer;
    }
}
