//Author: Alex Saletti
package Model;

public class Cell {
    //tipo di munizioni presenti nella cella, NULL se assenti
    private Ammo ammohere;
    //tipo di spawn presente, NULL se assente
    private SpawnPoint spawnpointzone;
    //cella presente in alto, NULL se c'è il muro
    private Cell upcell;
    //cella presente in basso, NULL se c'è il muro
    private Cell downcell;
    //cella presente a sinistra, NULL se c'è il muro
    private Cell leftcell;
    //cella presente a destra, NULL se c'è il muro
    private Cell rightcell;
    //id della cella
    private int cellid;


    //costruttore
    public Cell(int id){
        this.cellid=id;
        this.ammohere =null;
        this.downcell =null;
        this.leftcell =null;
        this.upcell =null;
        this.rightcell =null;
        this.spawnpointzone =null;
    }

    //metodi che restituiscono le celle adiacenti
    public Cell getUpcell() {
        return upcell;
    }

    public Cell getDowncell() {
        return downcell;
    }

    public Cell getLeftcell() {
        return leftcell;
    }

    public Cell getRightcell() {
        return rightcell;
    }

    //metodi che settano le celle adiacenti
    public void setUpcell(Cell upcell) {
        this.upcell = upcell;
    }

    public void setDowncell(Cell downcell) {
        this.downcell = downcell;
    }

    public void setLeftcell(Cell leftcell) {
        this.leftcell = leftcell;
    }

    public void setRightcell(Cell rightcell) {
        this.rightcell = rightcell;
    }

    //restituisce l'ID della cella
    public int getCellId() {
        return cellid;
    }

    //setta l'ID della cella
    public void setCellId(int id) {
        cellid = id;
    }

    //restituisce il tipo di munizioni presenti, NULL se assenti
    public Ammo getAmmohere() {
        return ammohere;
    }

    //setta il tipo di munizioni presenti
    public void setAmmohere(Ammo ammohere) {
        this.ammohere = ammohere;
    }

    //restituisce l'oggetto spawn, NULL se assente
    public SpawnPoint getSpawnpointzone() { return spawnpointzone; }

    //setta l'oggetto dello spawn
    public void setSpawnpointzone(SpawnPoint spawnpointzone) {
        this.spawnpointzone = spawnpointzone;
    }

}

