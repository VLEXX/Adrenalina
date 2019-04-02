//Author: Federico Scatà
package Model;

public class TurnChooseCharacter {
    private Player ChoosingTurn;

    public TurnChooseCharacter(){
        this.ChoosingTurn=null;
    }
    public Player getChoosingTurn() {
        return this.ChoosingTurn;
    }

    public void setChoosingTurn(Player choosingTurn) {
        this.ChoosingTurn = choosingTurn;
    }
}
