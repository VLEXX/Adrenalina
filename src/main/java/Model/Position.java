//Author: Federico Scatà
package Model;

//Classe che memorizza e identifica la posizione di un giocatore
public class Position {
    private Cell currentcell;   //cella corrente
    private Room currentroom;   //stanza corrente

    //Costruttore che setta cella e stanza a "null"
    public Position(){
        this.currentcell =null;
        this.currentroom =null;
    }

    //Ritorna la cella corrente
    public Cell getCurrentcell() {
        return currentcell;
    }

    //Setta la cella corrente
    public void setCurrentcell(Cell currentcell) {
        this.currentcell = currentcell;
    }

    //Ritorna la stanza corrente
    public Room getCurrentroom() {
        return currentroom;
    }

    //Setta la stanza corrente
    public void setCurrentroom(Room currentroom) {
        this.currentroom = currentroom;
    }
}
