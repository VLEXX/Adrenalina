//Author: Alex Saletti
package Model;

import java.util.ArrayList;

public class Room {
    private ArrayList<Cell> cellslist;    //elenco delle celle della stanza


    public Room(){
        cellslist = new ArrayList<>();
    }     //costruttore

    public void addCellsList(Cell c) {     //funzione per settare gli elementi dell'array per n da 0 a 3, errore altrimenti
        cellslist.add(c);
    }

    public ArrayList<Cell> getCellsList() {
        return cellslist;
    }
}
