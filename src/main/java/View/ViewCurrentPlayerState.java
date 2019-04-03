//Author: Federico Scatà
package View;

import Model.Action;
import Model.PlayerBoard;
import Model.Position;

//Classe che mantiene lo stato attuale del giocatore per la View
public class ViewCurrentPlayerState {
    private boolean ViewTurn;           //"true" se è il turno del giocatore(this), "false" altrimenti
    private int ActionCunter;           //contatore delle azioni (max 2)
    private Action ActionState;         //indica lo stato azione in cui si trova il giocatore(this), esempio "spara", "spostati",...
    private Position PlayerPosition;    //indica la posizione del giocatore
    private PlayerBoard Board;          //memorizza lo stato della plancia giocatore per la view

    //Costruttore
    public ViewCurrentPlayerState(){
        this.ViewTurn=false;
        this.ActionCunter=2;
        this.ActionState=null;
        this.PlayerPosition=null;
    }

    //ritorna lo stato del turno ("true" se attivo)
    public boolean isViewTurn() {
        return ViewTurn;
    }

    //setta lo stato del turno
    public void setViewTurn(boolean viewTurn) {
        ViewTurn = viewTurn;
    }

    //ritorna lo stato azion (spara, spostati, ecc...)
    public Action getActionState() {
        return ActionState;
    }

    //setta lo stato azione
    public void setActionState(Action actionState) {
        ActionState = actionState;
    }

    //ritorna la posizione del giocatore
    public Position getPlayerPosition() {
        return PlayerPosition;
    }

    //setta la posizione del giocatore
    public void setPlayerPosition(Position playerPosition) {
        PlayerPosition = playerPosition;
    }

    //ritorna il contatore delle azioni rimanenti
    public int getActionCunter() {
        return ActionCunter;
    }

    //resetta il contatore a 2
    public void resetActionCunter() {
        ActionCunter = 2;
    }

    //decresce il contatore di 1
    public void decreaseActionCounter(){
        this.ActionCunter = this.ActionCunter -1;
    }

    //ritorna lo stato della plancia giocatore
    public PlayerBoard getBoard() {
        return Board;
    }

    //setta lo stato della plancia giocatore
    public void setBoard(PlayerBoard board) {
        Board = board;
    }
}