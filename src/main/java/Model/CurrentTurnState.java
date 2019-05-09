/**
 * @author Federico Scatà
 */
package Model;

import Model.Observer;
import Model.Player;
import Model.Subject;

import java.util.ArrayList;

//Classe che memorizza lo stato attuale della del turno della partita
public class CurrentTurnState implements Subject {
    private Player playerturn;  //colore/personaggio del giocatore di cui è il turno attuale
    private ArrayList<Observer> observers;

    //Costruttore che setta il turno a "null"
    public CurrentTurnState(){
        this.playerturn = null;
        this.observers= new ArrayList<>();
    }

    //Ritorna il turno attuale
    public Player getPlayerturn(){
        return playerturn;
    }

    //Setta il turno attuale
    public void setPlayerturn(Player p){
        playerturn = p;
    }

    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObserver(){
        for(Observer observer: this.getObservers()){
            observer.update(this.getPlayerturn());
        }
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
