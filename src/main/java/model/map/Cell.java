/**
 * @author Alex Saletti && Giulia Rivara
 */
package model.map;

import model.munitions.Ammo;
import model.datapacket.MessageEnum;
import model.playerdata.Player;

import java.util.ArrayList;

/**
 * this class is used to build the entire map and each cell represents a square of the selected map
 */
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
    private int id;
    //celle dove è possibile spostarsi
    private ArrayList<Cell> reachableCells;
    //giocatore presente nella cella
    private ArrayList<Player> inCellPlayer;


    /**
     * construct a cell having the parameter id as id
     *  @param id cell id
     */
    public Cell(int id) {
        this.id = id;
        this.ammohere = null;
        this.downcell = null;
        this.leftcell = null;
        this.upcell = null;
        this.rightcell = null;
        this.spawnpointzone = null;
        this.reachableCells = new ArrayList<Cell>();
        this.inCellPlayer = new ArrayList<Player>();
    }



    /**
     * the following 3 methods manage a list made of players who are located in the cell during the game
     */
    //metodo che ritorna i giocatori presenti nella cella
    public ArrayList<Player> getInCellPlayer() {
        return inCellPlayer;
    }

    //metodo che aggiunge un giocatore nella cella
    public MessageEnum addInCellPlayer(Player player){
        boolean found = false;
        for (int i = 0; i < inCellPlayer.size(); i++) {
            if (inCellPlayer.get(i) == player) {
                found = true;
                break;
            }
        }
        if (found)
            return MessageEnum.PLAYER_ALREADY_PRESENT;
        else
            inCellPlayer.add(player);
        return MessageEnum.OK;
    }

    //metodo che rimuove un giocatore da una cella
    public MessageEnum removeInCellPlayer(Player player){
        boolean found = false;
        for (int i = 0; i < inCellPlayer.size(); i++) {
            if (inCellPlayer.get(i) == player) {
                found = true;
                break;
            }
        }
        if (!found)
            return MessageEnum.PLAYER_NOT_FOUND;
        else
            inCellPlayer.remove(player);
        return MessageEnum.OK;
    }


    /**
     * the following 4 methods allow to know the near cells and return null if there is a wall
     */
    //metodi che restituiscono le celle adiacenti
    public Cell getUpCell() {
        return upcell;
    }

    public Cell getDownCell() {
        return downcell;
    }

    public Cell getLeftCell() {
        return leftcell;
    }

    public Cell getRightCell() {
        return rightcell;
    }


    /**
     * allow to set the near cell
     * @param upcell
     * @param downcell
     * @param leftcell
     * @param rightcell
     */
    //metodo che setta le celle adiacenti
    public void setCells(Cell upcell, Cell downcell, Cell leftcell, Cell rightcell) {
        this.upcell = upcell;
        this.downcell = downcell;
        this.leftcell = leftcell;
        this.rightcell = rightcell;
    }



    /**
     *the following 6 methods allow to set or get various class attributes
     */
    //restituisce l'ID della cella
    public int getCellId() {
        return id;
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
    public SpawnPoint getSpawnpointzone() {
        return spawnpointzone;
    }

    //setta l'oggetto dello spawn
    public void setSpawnpointzone(SpawnPoint spawnpointzone) {
        this.spawnpointzone = spawnpointzone;
    }

    //restituisce le celle raggiungibili
    public ArrayList<Cell> getReachableCells() {
        return reachableCells;
    }



    /**
     * creates a list of the cell that are reachable in 3 moves from the actual
     */
    //inizializza la lista delle celle raggiungibili in 3 spostamenti
    public void initializeReachableCells() {
        if (this.getUpCell() != null) {
            reachableCells.add(this.getUpCell());
            if (this.getUpCell().getUpCell() != null) {
                if (!reachableCells.contains(this.getUpCell().getUpCell()))
                    reachableCells.add(this.getUpCell().getUpCell());
                if (this.getUpCell().getUpCell().getUpCell() != null && !reachableCells.contains(this.getUpCell().getUpCell().getUpCell()))
                    reachableCells.add(this.getUpCell().getUpCell().getUpCell());
                if (this.getUpCell().getUpCell().getRightCell() != null && !reachableCells.contains(this.getUpCell().getUpCell().getRightCell()))
                    reachableCells.add(this.getUpCell().getUpCell().getRightCell());
                if (this.getUpCell().getUpCell().getLeftCell() != null && !reachableCells.contains(this.getUpCell().getUpCell().getLeftCell()))
                    reachableCells.add(this.getUpCell().getUpCell().getLeftCell());
            }
            if (this.getUpCell().getLeftCell() != null) {
                if (!reachableCells.contains(this.getUpCell().getLeftCell()))
                    reachableCells.add(this.getUpCell().getLeftCell());
                if (this.getUpCell().getLeftCell().getUpCell() != null && !reachableCells.contains(this.getUpCell().getLeftCell().getUpCell()))
                    reachableCells.add(this.getUpCell().getLeftCell().getUpCell());
                if (this.getUpCell().getLeftCell().getDownCell() != null && !reachableCells.contains(this.getUpCell().getLeftCell().getDownCell()))
                    reachableCells.add(this.getUpCell().getLeftCell().getDownCell());
                if (this.getUpCell().getLeftCell().getLeftCell() != null && !reachableCells.contains(this.getUpCell().getLeftCell().getLeftCell()))
                    reachableCells.add(this.getUpCell().getLeftCell().getLeftCell());
            }
            if (this.getUpCell().getRightCell() != null) {
                if (!reachableCells.contains(this.getUpCell().getRightCell()))
                    reachableCells.add(this.getUpCell().getRightCell());
                if (this.getUpCell().getRightCell().getUpCell() != null && !reachableCells.contains(this.getUpCell().getRightCell().getUpCell()))
                    reachableCells.add(this.getUpCell().getRightCell().getUpCell());
                if (this.getUpCell().getRightCell().getDownCell() != null && !reachableCells.contains(this.getUpCell().getRightCell().getDownCell()))
                    reachableCells.add(this.getUpCell().getRightCell().getDownCell());
                if (this.getUpCell().getRightCell().getRightCell() != null && !reachableCells.contains(this.getUpCell().getRightCell().getRightCell()))
                    reachableCells.add(this.getUpCell().getRightCell().getRightCell());
            }
        }
        if (this.getDownCell() != null) {
            if (!reachableCells.contains(this.getDownCell()))
                reachableCells.add(this.getDownCell());
            if (this.getDownCell().getDownCell() != null) {
                if (!reachableCells.contains(this.getDownCell().getDownCell()))
                    reachableCells.add(this.getDownCell().getDownCell());
                if (this.getDownCell().getDownCell().getDownCell() != null && !reachableCells.contains(this.getDownCell().getDownCell().getDownCell()))
                    reachableCells.add(this.getDownCell().getDownCell().getDownCell());
                if (this.getDownCell().getDownCell().getRightCell() != null && !reachableCells.contains(this.getDownCell().getDownCell().getRightCell()))
                    reachableCells.add(this.getDownCell().getDownCell().getRightCell());
                if (this.getDownCell().getDownCell().getLeftCell() != null && !reachableCells.contains(this.getDownCell().getDownCell().getLeftCell()))
                    reachableCells.add(this.getDownCell().getDownCell().getLeftCell());
            }
            if (this.getDownCell().getLeftCell() != null) {
                if (!reachableCells.contains(this.getDownCell().getLeftCell()))
                    reachableCells.add(this.getDownCell().getLeftCell());
                if (this.getDownCell().getLeftCell().getUpCell() != null && !reachableCells.contains(this.getDownCell().getLeftCell().getUpCell()))
                    reachableCells.add(this.getDownCell().getLeftCell().getUpCell());
                if (this.getDownCell().getLeftCell().getDownCell() != null && !reachableCells.contains(this.getDownCell().getLeftCell().getDownCell()))
                    reachableCells.add(this.getDownCell().getLeftCell().getDownCell());
                if (this.getDownCell().getLeftCell().getLeftCell() != null && !reachableCells.contains(this.getDownCell().getLeftCell().getLeftCell()))
                    reachableCells.add(this.getDownCell().getLeftCell().getLeftCell());
            }
            if (this.getDownCell().getRightCell() != null) {
                if (!reachableCells.contains(this.getDownCell().getRightCell()))
                    reachableCells.add(this.getDownCell().getRightCell());
                if (this.getDownCell().getRightCell().getUpCell() != null && !reachableCells.contains(this.getDownCell().getRightCell().getUpCell()))
                    reachableCells.add(this.getDownCell().getRightCell().getUpCell());
                if (this.getDownCell().getRightCell().getDownCell() != null && !reachableCells.contains(this.getDownCell().getRightCell().getDownCell()))
                    reachableCells.add(this.getDownCell().getRightCell().getDownCell());
                if (this.getDownCell().getRightCell().getRightCell() != null && !reachableCells.contains(this.getDownCell().getRightCell().getRightCell()))
                    reachableCells.add(this.getDownCell().getRightCell().getRightCell());
            }
        }
        if (this.getLeftCell() != null) {
            if (!reachableCells.contains(this.getLeftCell()))
                reachableCells.add(this.getLeftCell());
            if (this.getLeftCell().getDownCell() != null) {
                if (!reachableCells.contains(this.getLeftCell().getDownCell()))
                    reachableCells.add(this.getLeftCell().getDownCell());
                if (this.getLeftCell().getDownCell().getDownCell() != null && !reachableCells.contains(this.getLeftCell().getDownCell().getDownCell()))
                    reachableCells.add(this.getLeftCell().getDownCell().getDownCell());
                if (this.getLeftCell().getDownCell().getRightCell() != null && !reachableCells.contains(this.getLeftCell().getDownCell().getRightCell()))
                    reachableCells.add(this.getLeftCell().getDownCell().getRightCell());
                if (this.getLeftCell().getDownCell().getLeftCell() != null && !reachableCells.contains(this.getLeftCell().getDownCell().getLeftCell()))
                    reachableCells.add(this.getLeftCell().getDownCell().getLeftCell());
            }
            if (this.getLeftCell().getLeftCell() != null) {
                if (!reachableCells.contains(this.getLeftCell().getLeftCell()))
                    reachableCells.add(this.getLeftCell().getLeftCell());
                if (this.getLeftCell().getLeftCell().getUpCell() != null && !reachableCells.contains(this.getLeftCell().getLeftCell().getUpCell()))
                    reachableCells.add(this.getLeftCell().getLeftCell().getUpCell());
                if (this.getLeftCell().getLeftCell().getDownCell() != null && !reachableCells.contains(this.getLeftCell().getLeftCell().getDownCell()))
                    reachableCells.add(this.getLeftCell().getLeftCell().getDownCell());
                if (this.getLeftCell().getLeftCell().getLeftCell() != null && !reachableCells.contains(this.getLeftCell().getLeftCell().getLeftCell()))
                    reachableCells.add(this.getLeftCell().getLeftCell().getLeftCell());
            }
            if (this.getLeftCell().getUpCell() != null) {
                if (!reachableCells.contains(this.getLeftCell().getUpCell()))
                    reachableCells.add(this.getLeftCell().getUpCell());
                if (this.getLeftCell().getUpCell().getUpCell() != null && !reachableCells.contains(this.getLeftCell().getUpCell().getUpCell()))
                    reachableCells.add(this.getLeftCell().getUpCell().getUpCell());
                if (this.getLeftCell().getUpCell().getLeftCell() != null && !reachableCells.contains(this.getLeftCell().getUpCell().getLeftCell()))
                    reachableCells.add(this.getLeftCell().getUpCell().getLeftCell());
                if (this.getLeftCell().getUpCell().getRightCell() != null && !reachableCells.contains(this.getLeftCell().getUpCell().getRightCell()))
                    reachableCells.add(this.getLeftCell().getUpCell().getRightCell());
            }
        }
        if (this.getRightCell() != null) {
            if (!reachableCells.contains(this.getRightCell()))
                reachableCells.add(this.getRightCell());
            if (this.getRightCell().getDownCell() != null) {
                if (!reachableCells.contains(this.getRightCell().getDownCell()))
                    reachableCells.add(this.getRightCell().getDownCell());
                if (this.getRightCell().getDownCell().getDownCell() != null && !reachableCells.contains(this.getRightCell().getDownCell().getDownCell()))
                    reachableCells.add(this.getRightCell().getDownCell().getDownCell());
                if (this.getRightCell().getDownCell().getRightCell() != null && !reachableCells.contains(this.getRightCell().getDownCell().getRightCell()))
                    reachableCells.add(this.getRightCell().getDownCell().getRightCell());
                if (this.getRightCell().getDownCell().getLeftCell() != null && !reachableCells.contains(this.getRightCell().getDownCell().getLeftCell()))
                    reachableCells.add(this.getRightCell().getDownCell().getLeftCell());
            }
            if (this.getRightCell().getRightCell() != null) {
                if (!reachableCells.contains(this.getRightCell().getRightCell()))
                    reachableCells.add(this.getRightCell().getRightCell());
                if (this.getRightCell().getRightCell().getUpCell() != null && !reachableCells.contains(this.getRightCell().getRightCell().getUpCell()))
                    reachableCells.add(this.getRightCell().getRightCell().getUpCell());
                if (this.getRightCell().getRightCell().getDownCell() != null && !reachableCells.contains(this.getRightCell().getRightCell().getDownCell()))
                    reachableCells.add(this.getRightCell().getRightCell().getDownCell());
                if (this.getRightCell().getRightCell().getRightCell() != null && !reachableCells.contains(this.getRightCell().getRightCell().getRightCell()))
                    reachableCells.add(this.getRightCell().getRightCell().getRightCell());
            }
            if (this.getRightCell().getUpCell() != null) {
                if (!reachableCells.contains(this.getRightCell().getUpCell()))
                    reachableCells.add(this.getRightCell().getUpCell());
                if (this.getRightCell().getUpCell().getUpCell() != null && !reachableCells.contains(this.getRightCell().getUpCell().getUpCell()))
                    reachableCells.add(this.getRightCell().getUpCell().getUpCell());
                if (this.getRightCell().getUpCell().getLeftCell() != null && !reachableCells.contains(this.getRightCell().getUpCell().getLeftCell()))
                    reachableCells.add(this.getRightCell().getUpCell().getLeftCell());
                if (this.getRightCell().getUpCell().getRightCell() != null && !reachableCells.contains(this.getRightCell().getUpCell().getRightCell()))
                    reachableCells.add(this.getRightCell().getUpCell().getRightCell());
            }
        }
    }

}

