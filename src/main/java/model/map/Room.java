//Author: Alex Saletti
package model.map;

import java.util.ArrayList;

public class Room {
    private int id; //id della stanza
    private ArrayList<Cell> cellslist;    //elenco delle celle della stanza


    public Room(int id) {
        this.id = id;
        cellslist = new ArrayList<>();
    }     //costruttore

    public int getRoomId() {    //restituisce l'id
        return id;
    }

    public void addCellsList(Cell c) {     //funzione per settare gli elementi dell'array per n da 0 a 3, errore altrimenti
        cellslist.add(c);
    }

    public ArrayList<Cell> getCellsList() {
        return cellslist;
    }
}
