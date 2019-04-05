//Author: Federico Scatà
package View;

import Model.Action;
import Model.PlayerBoard;
import Model.Position;

//Classe che mantiene lo stato attuale del giocatore per la View
public class ViewCurrentPlayerState {
    private boolean viewturn;           //"true" se è il turno del giocatore(this), "false" altrimenti
    private int actioncounter;           //contatore delle azioni (max 2)
    private Action actionstate;         //indica lo stato azione in cui si trova il giocatore(this), esempio "spara", "spostati",...
    private Position playerposition;    //indica la posizione del giocatore
    private PlayerBoard board;          //memorizza lo stato della plancia giocatore per la view

    //Costruttore
    public ViewCurrentPlayerState(){
        this.viewturn =false;
        this.actioncounter =2;
        this.actionstate =null;
        this.playerposition =null;
    }

    //ritorna lo stato del turno ("true" se attivo)
    public boolean isViewturn() {
        return viewturn;
    }

    //setta lo stato del turno
    public void setViewturn(boolean viewturn) {
        this.viewturn = viewturn;
    }

    //ritorna lo stato azion (spara, spostati, ecc...)
    public Action getActionstate() {
        return actionstate;
    }

    //setta lo stato azione
    public void setActionstate(Action actionstate) {
        this.actionstate = actionstate;
    }

    //ritorna la posizione del giocatore
    public Position getPlayerposition() {
        return playerposition;
    }

    //setta la posizione del giocatore
    public void setPlayerposition(Position playerposition) {
        this.playerposition = playerposition;
    }

    //ritorna il contatore delle azioni rimanenti
    public int getActioncounter() {
        return actioncounter;
    }

    //resetta il contatore a 2
    public void resetActionCunter() {
        actioncounter = 2;
    }

    //decresce il contatore di 1
    public void decreaseActionCounter(){
        this.actioncounter = this.actioncounter -1;
    }

    //ritorna lo stato della plancia giocatore
    public PlayerBoard getBoard() {
        return board;
    }

    //setta lo stato della plancia giocatore
    public void setBoard(PlayerBoard board) {
        this.board = board;
    }
}