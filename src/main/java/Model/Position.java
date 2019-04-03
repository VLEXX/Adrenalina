//Author: Federico Scatà
package Model;

//Classe che memorizza e identifica la posizione di un giocatore
public class Position {
    private Cell CurrentCell;   //cella corrente
    private Room CurrentRoom;   //stanza corrente

    //Costruttore che setta cella e stanza a "null"
    public Position(){
        this.CurrentCell=null;
        this.CurrentRoom=null;
    }

    //Ritorna la cella corrente
    public Cell getCurrentCell() {
        return CurrentCell;
    }

    //Setta la cella corrente
    public void setCurrentCell(Cell currentCell) {
        CurrentCell = currentCell;
    }

    //Ritorna la stanza corrente
    public Room getCurrentRoom() {
        return CurrentRoom;
    }

    //Setta la stanza corrente
    public void setCurrentRoom(Room currentRoom) {
        CurrentRoom = currentRoom;
    }
}
