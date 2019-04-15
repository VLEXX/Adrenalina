//Author: Federico Scatà
package View;


import Model.CurrentPlayerState;
import Model.Player;

//Classe che mantiene lo stato attuale del giocatore per la View
public class ViewCurrentPlayerState {
    private CurrentPlayerState currentPlayerState; //memorizza lo stato corrente del giocatore per la view
    private Player activeplayer;                   //identifica il colore del giocatore di this

    public ViewCurrentPlayerState(){
        currentPlayerState = null;
        activeplayer = null;
    }

    public CurrentPlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public Player getActiveplayer() {
        return activeplayer;
    }

    public void setCurrentPlayerState(CurrentPlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;
    }

    public void setActiveplayer(Player activeplayer) {
        this.activeplayer = activeplayer;
    }
}