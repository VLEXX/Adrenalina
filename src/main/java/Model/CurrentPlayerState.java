//Author: Federico Scatà
package Model;

import java.io.Serializable;

//Classe che memorizza lo stato attuale del giocatore
public class CurrentPlayerState implements Observer, Serializable {
    private Position playerposition;    //Posizione attuale del giocatore
    private boolean activeturn;         //"true" se è il proprio turno "false" altrimenti
    private PlayerBoard board;          //PlanciaGiocatore
    private Action actionstate;         //Memorizza lo stato in cui si trova il giocatore (spara, spostati,...)
    private int actioncounter;          //Contatore di azioni rimanenti che può compiere il giocatore (max 2 per turno)
    private Player activeplayer;        //Memorizza il colore/personaggio scelto dal giocatore

    //Costruttore che setta tutti gli attributi a "null" e il contatore al massimo (cioè 2)
    public CurrentPlayerState(){
        this.playerposition =null;
        this.activeturn =false;
        this.board =null;
        this.actionstate =null;
        this.actioncounter =2;
        this.activeplayer =null;
    }

    //Ritorna la Posizione
    public Position getPlayerposition() {
        return this.playerposition;
    }

    //Setta la Posizione
    public void setPlayerposition(Position playerposition) {
        this.playerposition = playerposition;
    }

    //Ritorna boolean "true" se è il turno del giocatore(this), "false" altrimenti
    public boolean isActiveturn() {
        return this.activeturn;
    }

    //Cambia lo stato del boolean "activeturn"
    public void setActiveturn(boolean activeturn) {
        this.activeturn = activeturn;
    }

    //Ritorna la PlanciaGiocatore
    public PlayerBoard getBoard() {
        return this.board;
    }

    //Setta la PlanciaGiocatore
    public void setBoard(PlayerBoard board) {
        this.board = board;
    }

    //Ritorna lo stato "azione" del giocatore
    public Action getActionstate() {
        return this.actionstate;
    }

    //Setta lo stato "azione" del giocatore
    public void setActionstate(Action actionstate) {
        this.actionstate = actionstate;
    }

    //Ritorna il contantore delle azioni rimanenti
    public int getActioncounter() {
        return this.actioncounter;
    }

    //Resetta (riporta a 2) il contatore delle azioni rimanenti
    public void resetActionCounter() {
        this.actioncounter = 2;
    }

    //Fa decrescere di 1 il contatore delle azioni rimanenti
    public void decreaseActionCounter() {
        this.actioncounter = actioncounter -1;
    }

    //Ritorna il colore/personaggio scelto dal giocatore
    public Player getActiveplayer() {
        return this.activeplayer;
    }

    //Setta il colore/personaggio scelto dal giocatore
    public void setActiveplayer(Player activeplayer) {
        this.activeplayer = activeplayer;
    }

    @Override
    public void update(Player p) {
        if(p == this.activeplayer){
            this.setActiveturn(true);
        }
    }
}
