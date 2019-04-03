//Author: Federico Scatà
package Model;

//Classe che memorizza lo stato attuale della del turno della partita
public class CurrentTurnState {
    private Player PlayerTurn;  //colore/personaggio del giocatore di cui è il turno attuale

    //Costruttore che setta il turno a "null"
    public CurrentTurnState(){
        this.PlayerTurn=null;
    }

    //Ritorna il turno attuale
    public Player getPlayerTurn(){
        return PlayerTurn;
    }

    //Setta il turno attuale
    public void setPlayerTurn(Player p){
        PlayerTurn=p;
    }
}
