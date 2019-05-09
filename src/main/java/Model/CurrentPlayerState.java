/**
 * @author Federico Scatà
 */
package Model;

import Model.Observer;
import Model.Player;
import Model.PlayerBoard;
import Model.Position;
import java.io.Serializable;

/**
 * Classe che memorizza lo stato attuale del giocatore
 */
public class CurrentPlayerState implements Observer, Serializable {
    private Position playerposition;    //Posizione attuale del giocatore
    private boolean activeturn;         //"true" se è il proprio turno "false" altrimenti
    private PlayerBoard board;          //PlanciaGiocatore
    private int actioncounter;          //Contatore di azioni rimanenti che può compiere il giocatore (max 2 per turno)
    private Player activeplayer;        //Memorizza il colore/personaggio scelto dal giocatore

    /**
     * Costruttore che setta tutti gli attributi a "null" e il contatore al massimo (cioè 2)
     */
    public CurrentPlayerState(Player player){
        this.playerposition =null;
        this.activeturn =false;
        this.board =null;
        this.actioncounter =2;
        this.activeplayer = player;
    }

    /**
     * Metodo get che ritorna la posizione del giocatore
     */
    public Position getPlayerposition() {
        return this.playerposition;
    }

    /**
     * Metodo set che setta la posizione del giocatore
     *
     * @param playerposition di tipo Position
     */
    public void setPlayerposition(Position playerposition) {
        this.playerposition = playerposition;
    }

    /**
     * Metodo get che ritorna boolean "true" se è il turno del giocatore(this), "false" altrimenti
     */
    public boolean isActiveturn() {
        return this.activeturn;
    }

    /**
     * Metodo set che setta il boolean ActiveTurn
     *
     * @param activeturn
     */
    public void setActiveturn(boolean activeturn) {
        this.activeturn = activeturn;
    }

    /**
     * Metodo get che ritorna la PlanciaGiocatore
     *
     * @return oggetto di tipo PlayerBoard
     */
    public PlayerBoard getBoard() {
        return this.board;
    }

    /**
     * Metodo set che setta la PlanciaGiocatore del giocatore
     *
     * @param board di tipo PlayerBoard
     */
    public void setBoard(PlayerBoard board) {
        this.board = board;
    }

    /**
     * Metodo get che ritorna actioncounter
     *
     * @return oggetto di tipo intero
     */
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
