//Author: Federico Scatà
package Model;

//Classe che memorizza lo stato attuale del giocatore
public class CurrentPlayerState {
    private Position PlayerPosition;    //Posizione attuale del giocatore
    private boolean ActiveTurn;         //"true" se è il proprio turno "false" altrimenti
    private PlayerBoard Board;          //PlanciaGiocatore
    private Action ActionState;         //Memorizza lo stato in cui si trova il giocatore (spara, spostati,...)
    private int ActionCounter;          //Contatore di azioni rimanenti che può compiere il giocatore (max 2 per turno)
    private Player ActivePlayer;        //Memorizza il colore/personaggio scelto dal giocatore

    //Costruttore che setta tutti gli attributi a "null" e il contatore al massimo (cioè 2)
    public CurrentPlayerState(){
        this.PlayerPosition=null;
        this.ActiveTurn=false;
        this.Board=null;
        this.ActionState=null;
        this.ActionCounter=2;
        this.ActivePlayer=null;
    }

    //Ritorna la Posizione
    public Position getPlayerPosition() {
        return this.PlayerPosition;
    }

    //Setta la Posizione
    public void setPlayerPosition(Position playerPosition) {
        this.PlayerPosition = playerPosition;
    }

    //Ritorna boolean "true" se è il turno del giocatore(this), "false" altrimenti
    public boolean isActiveTurn() {
        return this.ActiveTurn;
    }

    //Cambia lo stato del boolean "ActiveTurn"
    public void setActiveTurn(boolean activeTurn) {
        this.ActiveTurn = activeTurn;
    }

    //Ritorna la PlanciaGiocatore
    public PlayerBoard getBoard() {
        return this.Board;
    }

    //Setta la PlanciaGiocatore
    public void setBoard(PlayerBoard board) {
        this.Board = board;
    }

    //Ritorna lo stato "azione" del giocatore
    public Action getActionState() {
        return this.ActionState;
    }

    //Setta lo stato "azione" del giocatore
    public void setActionState(Action actionState) {
        this.ActionState = actionState;
    }

    //Ritorna il contantore delle azioni rimanenti
    public int getActionCounter() {
        return this.ActionCounter;
    }

    //Resetta (riporta a 2) il contatore delle azioni rimanenti
    public void resetActionCounter() {
        this.ActionCounter = 2;
    }

    //Fa decrescere di 1 il contatore delle azioni rimanenti
    public void decreaseActionCounter() {
        this.ActionCounter = ActionCounter-1;
    }

    //Ritorna il colore/personaggio scelto dal giocatore
    public Player getActivePlayer() {
        return this.ActivePlayer;
    }

    //Setta il colore/personaggio scelto dal giocatore
    public void setActivePlayer(Player activePlayer) {
        this.ActivePlayer = activePlayer;
    }
}
