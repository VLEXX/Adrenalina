//Author: Alex Saletti
package Model;

import java.util.ArrayList;

public class Room {
    private ArrayList<Cell> cellslist;    //elenco delle celle della stanza


    public Room(){
        this.cellslist=null;
    }     //costruttore

    public void setCellslistElem(ArrayList<Cell> c) {     //funzione per settare gli elementi dell'array per n da 0 a 3, errore altrimenti
        this.cellslist = c;
    }

    public ArrayList<Cell> getCellslist() {
        return cellslist;
    }
}
