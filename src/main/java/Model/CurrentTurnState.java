//Author: Federico Scatà
package Model;

//Classe che memorizza lo stato attuale della del turno della partita
public class CurrentTurnState {
    private Player playerturn;  //colore/personaggio del giocatore di cui è il turno attuale

    //Costruttore che setta il turno a "null"
    public CurrentTurnState(){
        this.playerturn =null;
    }

    //Ritorna il turno attuale
    public Player getPlayerturn(){
        return playerturn;
    }

    //Setta il turno attuale
    public void setPlayerturn(Player p){
        playerturn =p;
    }
}
