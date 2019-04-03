//Author: Alex Saletti
package Model;

public class Cell {
    //tipo di munizioni presenti nella cella, NULL se assenti
    private Ammo AmmoHere;
    //tipo di spawn presente, NULL se assente
    private SpawnPoint SpawnPointZone;
    //cella presente in alto, NULL se c'è il muro
    private Cell UpCell;
    //cella presente in basso, NULL se c'è il muro
    private Cell DownCell;
    //cella presente a sinistra, NULL se c'è il muro
    private Cell LeftCell;
    //cella presente a destra, NULL se c'è il muro
    private Cell RightCell;
    //id della cella
    private int CellId;


    //costruttore
    public Cell(int id){
        this.CellId=id;
        this.AmmoHere=null;
        this.DownCell=null;
        this.LeftCell=null;
        this.UpCell=null;
        this.RightCell=null;
        this.SpawnPointZone=null;
    }

    //metodi che restituiscono le celle adiacenti
    public Cell getUpCell() {
        return UpCell;
    }

    public Cell getDownCell() {
        return DownCell;
    }

    public Cell getLeftCell() {
        return LeftCell;
    }

    public Cell getRightCell() {
        return RightCell;
    }

    //metodi che settano le celle adiacenti
    public void setUpCell(Cell upCell) {
        UpCell = upCell;
    }

    public void setDownCell(Cell downCell) {
        DownCell = downCell;
    }

    public void setLeftCell(Cell leftCell) {
        LeftCell = leftCell;
    }

    public void setRightCell(Cell rightCell) {
        RightCell = rightCell;
    }

    //restituisce l'ID della cella
    public int getCellId() {
        return CellId;
    }

    //setta l'ID della cella
    public void setCellId(int cellId) {
        CellId = cellId;
    }

    //restituisce il tipo di munizioni presenti, NULL se assenti
    public Ammo getAmmoHere() {
        return AmmoHere;
    }

    //setta il tipo di munizioni presenti
    public void setAmmoHere(Ammo ammoHere) {
        AmmoHere = ammoHere;
    }

    //restituisce l'oggetto spawn, NULL se assente
    public SpawnPoint getSpawnPointZone() { return SpawnPointZone; }

    //setta l'oggetto dello spawn
    public void setSpawnPointZone(SpawnPoint spawnPointZone) {
        SpawnPointZone = spawnPointZone;
    }

}

