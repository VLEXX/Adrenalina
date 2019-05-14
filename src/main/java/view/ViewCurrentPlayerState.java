/**
 * @author Federico Scatà
 */
package view;


import model.playerdata.CurrentPlayerState;

//Classe che mantiene lo stato attuale del giocatore per la view
public class ViewCurrentPlayerState {
    private CurrentPlayerState currentPlayerState; //memorizza lo stato corrente del giocatore per la view

    public ViewCurrentPlayerState(){
        currentPlayerState = null;
    }

    public CurrentPlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setCurrentPlayerState(CurrentPlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;
    }
}