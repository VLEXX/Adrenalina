/**
 * @author Federico Scatà
 */
package model.map;

import java.io.*;

//Classe che memorizza e identifica la posizione di un giocatore
public class Position implements Serializable{
    private Cell currentcell;   //cella corrente
    private Room currentroom;   //stanza corrente

    //Costruttore che setta cella e stanza a "null"
    public Position() {
        this.currentcell = null;
        this.currentroom = null;
    }

    //Ritorna la cella corrente
    public Cell getCurrentcell() {
        return currentcell;
    }

    //Setta la cella corrente
    public synchronized void setCurrentcell(Cell currentcell) {
        this.currentcell = currentcell;
    }

    //Ritorna la stanza corrente
    public Room getCurrentroom() {
        return currentroom;
    }

    //Setta la stanza corrente
    public synchronized void setCurrentroom(Room currentroom) {
        this.currentroom = currentroom;
    }

    public Position deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(this);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return (Position) objectInputStream.readObject();
    }
}
