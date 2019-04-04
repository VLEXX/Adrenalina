//Author: Alex Saletti
package Model;

public class Room {
    private Cell[] CellsList = new Cell[4];     //elenco delle celle della stanza


    public Room(){ }     //costruttore

    public void setCellslistElem(Cell c,int n) {     //funzione per settare gli elementi dell'array per n da 0 a 3, errore altrimenti
        CellsList[n] = c;
    }

    public Cell getCellslistElem(int n){        //metodo che restituisce l'elemento richiesto per n da 0 a 3, errore altrimenti
        return CellsList[n];
    }
}
