package Model;

public class CurrentTurnState {
    private Player PlayerTurn;

    public CurrentTurnState(){
        this.PlayerTurn=null;
    }

    public Player getPlayerTurn(){
        return PlayerTurn;
    }

    public void setPlayerTurn(Player p){
        PlayerTurn=p;
    }
}
