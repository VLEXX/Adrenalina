/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.Observer;
import model.playerdata.Player;

import java.util.ArrayList;

//Classe che memorizza lo stato attuale della del turno della partita
public class CurrentTurnState {
    private Player playerturn;  //colore/personaggio del giocatore di cui è il turno attuale
    private ArrayList<Observer> observers;

    //Costruttore che setta il turno a "null"
    public CurrentTurnState() {
        this.playerturn = null;
        this.observers = new ArrayList<>();
    }

    /**
     * @return playerturn
     */
    public Player getPlayerturn() {
        return playerturn;
    }

    //Setta il turno attuale
    public void setPlayerturn(Player p) {
        playerturn = p;
    }

}
