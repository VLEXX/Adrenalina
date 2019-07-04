/**
 * @author Alex Saletti & Giulia Rivara
 */
package model.map;

import model.munitions.Ammo;
import model.datapacket.MessageEnum;
import model.playerdata.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is used to build the entire map and each cell represents a square of the selected map
 */
public class Cell implements Serializable {
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
    //celle raggiungibili con 2 spostamenti unitari
    private ArrayList<Cell> reachable2Cells;
    //celle raggiungibili con 3 spostamenti unitari
    private ArrayList<Cell> reachable3Cells;
    //giocatore presente nella cella
    private ArrayList<Player> inCellPlayer;
    //celle visibili da un giocatore
    private ArrayList<Cell> visibleCells;

    /**
     * Construct a cell having the parameter id as id
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
        this.reachable2Cells = new ArrayList<>();
        this.reachable3Cells = new ArrayList<>();
        this.inCellPlayer = new ArrayList<>();
        this.visibleCells = new ArrayList<>();
    }

    /**
     * Initialize the visible cell from a player
     * @param map
     * @author Giulia Rivara
     */
    public void initializeVisibleCells(Map map){
        int ID = 0;
        for(int i = 0; i < map.getRoomList().size(); i++){
            for(int j = 0; j < map.getRoomList().get(i).getCellsList().size(); j++){
                if(this.getCellId() == map.getRoomList().get(i).getCellsList().get(j).getCellId()){
                    for(int k = 0; k < map.getRoomList().get(i).getCellsList().size(); k++)
                    visibleCells.add(map.getRoomList().get(i).getCellsList().get(k));
                    //visibleCells = map.getRoomList().get(i).getCellsList();
                    ID = map.getRoomList().get(i).getRoomId();
                    break;
                }
            }
        }
        if(this.getUpCell() != null){
            for(int i = 0; i < map.getRoomList().size(); i++){
                for(int j = 0; j < map.getRoomList().get(i).getCellsList().size(); j++){
                    if(this.getUpCell().getCellId() == map.getRoomList().get(i).getCellsList().get(j).getCellId()){
                        if(map.getRoomList().get(i).getRoomId() == ID){
                            break;
                        } else {
                            for(int k = 0; k < map.getRoomList().get(i).getCellsList().size(); k++){
                                visibleCells.add(map.getRoomList().get(i).getCellsList().get(k));
                            }
                            break;
                        }
                    }
                }
            }
        }
        if(this.getDownCell() != null){
            for(int i = 0; i < map.getRoomList().size(); i++){
                for(int j = 0; j < map.getRoomList().get(i).getCellsList().size(); j++){
                    if(this.getDownCell().getCellId() == map.getRoomList().get(i).getCellsList().get(j).getCellId()){
                        if(map.getRoomList().get(i).getRoomId() == ID){
                            break;
                        } else {
                            for(int k = 0; k < map.getRoomList().get(i).getCellsList().size(); k++){
                                visibleCells.add(map.getRoomList().get(i).getCellsList().get(k));
                            }
                            break;
                        }
                    }
                }
            }
        }
        if(this.getLeftCell() != null){
            for(int i = 0; i < map.getRoomList().size(); i++){
                for(int j = 0; j < map.getRoomList().get(i).getCellsList().size(); j++){
                    if(this.getLeftCell().getCellId() == map.getRoomList().get(i).getCellsList().get(j).getCellId()){
                        if(map.getRoomList().get(i).getRoomId() == ID){
                            break;
                        } else {
                            for(int k = 0; k < map.getRoomList().get(i).getCellsList().size(); k++){
                                visibleCells.add(map.getRoomList().get(i).getCellsList().get(k));
                            }
                            break;
                        }
                    }
                }
            }
        }
        if(this.getRightCell() != null){
            for(int i = 0; i < map.getRoomList().size(); i++){
                for(int j = 0; j < map.getRoomList().get(i).getCellsList().size(); j++){
                    if(this.getRightCell().getCellId() == map.getRoomList().get(i).getCellsList().get(j).getCellId()){
                        if(map.getRoomList().get(i).getRoomId() == ID){
                            break;
                        } else {
                            for(int k = 0; k < map.getRoomList().get(i).getCellsList().size(); k++){
                                visibleCells.add(map.getRoomList().get(i).getCellsList().get(k));
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * @return the visible cells from a player
     * @author Giulia Rivara
     */
    public ArrayList<Cell> getVisibleCells() {
        return visibleCells;
    }

    /**
     * the following 3 methods manage a list made of players who are located in the cell during the game
     */
    public ArrayList<Player> getInCellPlayer() {
        return inCellPlayer;
    }

    /**
     * Method add a player into a cell
     * @param player
     * @return OK
     * @author Giulia Rivara
     */
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

    /**
     * Method remove a player from a cell
     * @param player
     * @return OK
     * @author Giulia Rivara
     */
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
     * allow to set the near cells
     * @param upcell
     * @param downcell
     * @param leftcell
     * @param rightcell
     */
    //metodo che setta le celle adiacenti
    public synchronized void setCells(Cell upcell, Cell downcell, Cell leftcell, Cell rightcell) {
        this.upcell = upcell;
        this.downcell = downcell;
        this.leftcell = leftcell;
        this.rightcell = rightcell;
    }

    /**
     *the following 7 methods allow to set or get various class attributes
     */
    public int getCellId() {
        return id;
    }

    public Ammo getAmmohere() {
        return ammohere;
    }

    public synchronized void setAmmohere(Ammo ammohere) {
        this.ammohere = ammohere;
    }

    public SpawnPoint getSpawnpointzone() {
        return spawnpointzone;
    }

    public synchronized void setSpawnpointzone(SpawnPoint spawnpointzone) {
        this.spawnpointzone = spawnpointzone;
    }

    public ArrayList<Cell> getReachable3Cells() {
        return reachable3Cells;
    }

    public ArrayList<Cell> getReachable2Cells() {
        return reachable2Cells;
    }

    /**
     * creates a list of the cell that are reachable in 3 moves from the actual
     */
    public void initializeReachable3Cells() {
        if (this.getUpCell() != null) {
            reachable3Cells.add(this.getUpCell());
            if (this.getUpCell().getUpCell() != null) {
                if (!reachable3Cells.contains(this.getUpCell().getUpCell()))
                    reachable3Cells.add(this.getUpCell().getUpCell());
                if (this.getUpCell().getUpCell().getUpCell() != null && !reachable3Cells.contains(this.getUpCell().getUpCell().getUpCell()))
                    reachable3Cells.add(this.getUpCell().getUpCell().getUpCell());
                if (this.getUpCell().getUpCell().getRightCell() != null && !reachable3Cells.contains(this.getUpCell().getUpCell().getRightCell()))
                    reachable3Cells.add(this.getUpCell().getUpCell().getRightCell());
                if (this.getUpCell().getUpCell().getLeftCell() != null && !reachable3Cells.contains(this.getUpCell().getUpCell().getLeftCell()))
                    reachable3Cells.add(this.getUpCell().getUpCell().getLeftCell());
            }
            if (this.getUpCell().getLeftCell() != null) {
                if (!reachable3Cells.contains(this.getUpCell().getLeftCell()))
                    reachable3Cells.add(this.getUpCell().getLeftCell());
                if (this.getUpCell().getLeftCell().getUpCell() != null && !reachable3Cells.contains(this.getUpCell().getLeftCell().getUpCell()))
                    reachable3Cells.add(this.getUpCell().getLeftCell().getUpCell());
                if (this.getUpCell().getLeftCell().getDownCell() != null && !reachable3Cells.contains(this.getUpCell().getLeftCell().getDownCell()))
                    reachable3Cells.add(this.getUpCell().getLeftCell().getDownCell());
                if (this.getUpCell().getLeftCell().getLeftCell() != null && !reachable3Cells.contains(this.getUpCell().getLeftCell().getLeftCell()))
                    reachable3Cells.add(this.getUpCell().getLeftCell().getLeftCell());
            }
            if (this.getUpCell().getRightCell() != null) {
                if (!reachable3Cells.contains(this.getUpCell().getRightCell()))
                    reachable3Cells.add(this.getUpCell().getRightCell());
                if (this.getUpCell().getRightCell().getUpCell() != null && !reachable3Cells.contains(this.getUpCell().getRightCell().getUpCell()))
                    reachable3Cells.add(this.getUpCell().getRightCell().getUpCell());
                if (this.getUpCell().getRightCell().getDownCell() != null && !reachable3Cells.contains(this.getUpCell().getRightCell().getDownCell()))
                    reachable3Cells.add(this.getUpCell().getRightCell().getDownCell());
                if (this.getUpCell().getRightCell().getRightCell() != null && !reachable3Cells.contains(this.getUpCell().getRightCell().getRightCell()))
                    reachable3Cells.add(this.getUpCell().getRightCell().getRightCell());
            }
        }
        if (this.getDownCell() != null) {
            if (!reachable3Cells.contains(this.getDownCell()))
                reachable3Cells.add(this.getDownCell());
            if (this.getDownCell().getDownCell() != null) {
                if (!reachable3Cells.contains(this.getDownCell().getDownCell()))
                    reachable3Cells.add(this.getDownCell().getDownCell());
                if (this.getDownCell().getDownCell().getDownCell() != null && !reachable3Cells.contains(this.getDownCell().getDownCell().getDownCell()))
                    reachable3Cells.add(this.getDownCell().getDownCell().getDownCell());
                if (this.getDownCell().getDownCell().getRightCell() != null && !reachable3Cells.contains(this.getDownCell().getDownCell().getRightCell()))
                    reachable3Cells.add(this.getDownCell().getDownCell().getRightCell());
                if (this.getDownCell().getDownCell().getLeftCell() != null && !reachable3Cells.contains(this.getDownCell().getDownCell().getLeftCell()))
                    reachable3Cells.add(this.getDownCell().getDownCell().getLeftCell());
            }
            if (this.getDownCell().getLeftCell() != null) {
                if (!reachable3Cells.contains(this.getDownCell().getLeftCell()))
                    reachable3Cells.add(this.getDownCell().getLeftCell());
                if (this.getDownCell().getLeftCell().getUpCell() != null && !reachable3Cells.contains(this.getDownCell().getLeftCell().getUpCell()))
                    reachable3Cells.add(this.getDownCell().getLeftCell().getUpCell());
                if (this.getDownCell().getLeftCell().getDownCell() != null && !reachable3Cells.contains(this.getDownCell().getLeftCell().getDownCell()))
                    reachable3Cells.add(this.getDownCell().getLeftCell().getDownCell());
                if (this.getDownCell().getLeftCell().getLeftCell() != null && !reachable3Cells.contains(this.getDownCell().getLeftCell().getLeftCell()))
                    reachable3Cells.add(this.getDownCell().getLeftCell().getLeftCell());
            }
            if (this.getDownCell().getRightCell() != null) {
                if (!reachable3Cells.contains(this.getDownCell().getRightCell()))
                    reachable3Cells.add(this.getDownCell().getRightCell());
                if (this.getDownCell().getRightCell().getUpCell() != null && !reachable3Cells.contains(this.getDownCell().getRightCell().getUpCell()))
                    reachable3Cells.add(this.getDownCell().getRightCell().getUpCell());
                if (this.getDownCell().getRightCell().getDownCell() != null && !reachable3Cells.contains(this.getDownCell().getRightCell().getDownCell()))
                    reachable3Cells.add(this.getDownCell().getRightCell().getDownCell());
                if (this.getDownCell().getRightCell().getRightCell() != null && !reachable3Cells.contains(this.getDownCell().getRightCell().getRightCell()))
                    reachable3Cells.add(this.getDownCell().getRightCell().getRightCell());
            }
        }
        if (this.getLeftCell() != null) {
            if (!reachable3Cells.contains(this.getLeftCell()))
                reachable3Cells.add(this.getLeftCell());
            if (this.getLeftCell().getDownCell() != null) {
                if (!reachable3Cells.contains(this.getLeftCell().getDownCell()))
                    reachable3Cells.add(this.getLeftCell().getDownCell());
                if (this.getLeftCell().getDownCell().getDownCell() != null && !reachable3Cells.contains(this.getLeftCell().getDownCell().getDownCell()))
                    reachable3Cells.add(this.getLeftCell().getDownCell().getDownCell());
                if (this.getLeftCell().getDownCell().getRightCell() != null && !reachable3Cells.contains(this.getLeftCell().getDownCell().getRightCell()))
                    reachable3Cells.add(this.getLeftCell().getDownCell().getRightCell());
                if (this.getLeftCell().getDownCell().getLeftCell() != null && !reachable3Cells.contains(this.getLeftCell().getDownCell().getLeftCell()))
                    reachable3Cells.add(this.getLeftCell().getDownCell().getLeftCell());
            }
            if (this.getLeftCell().getLeftCell() != null) {
                if (!reachable3Cells.contains(this.getLeftCell().getLeftCell()))
                    reachable3Cells.add(this.getLeftCell().getLeftCell());
                if (this.getLeftCell().getLeftCell().getUpCell() != null && !reachable3Cells.contains(this.getLeftCell().getLeftCell().getUpCell()))
                    reachable3Cells.add(this.getLeftCell().getLeftCell().getUpCell());
                if (this.getLeftCell().getLeftCell().getDownCell() != null && !reachable3Cells.contains(this.getLeftCell().getLeftCell().getDownCell()))
                    reachable3Cells.add(this.getLeftCell().getLeftCell().getDownCell());
                if (this.getLeftCell().getLeftCell().getLeftCell() != null && !reachable3Cells.contains(this.getLeftCell().getLeftCell().getLeftCell()))
                    reachable3Cells.add(this.getLeftCell().getLeftCell().getLeftCell());
            }
            if (this.getLeftCell().getUpCell() != null) {
                if (!reachable3Cells.contains(this.getLeftCell().getUpCell()))
                    reachable3Cells.add(this.getLeftCell().getUpCell());
                if (this.getLeftCell().getUpCell().getUpCell() != null && !reachable3Cells.contains(this.getLeftCell().getUpCell().getUpCell()))
                    reachable3Cells.add(this.getLeftCell().getUpCell().getUpCell());
                if (this.getLeftCell().getUpCell().getLeftCell() != null && !reachable3Cells.contains(this.getLeftCell().getUpCell().getLeftCell()))
                    reachable3Cells.add(this.getLeftCell().getUpCell().getLeftCell());
                if (this.getLeftCell().getUpCell().getRightCell() != null && !reachable3Cells.contains(this.getLeftCell().getUpCell().getRightCell()))
                    reachable3Cells.add(this.getLeftCell().getUpCell().getRightCell());
            }
        }
        if (this.getRightCell() != null) {
            if (!reachable3Cells.contains(this.getRightCell()))
                reachable3Cells.add(this.getRightCell());
            if (this.getRightCell().getDownCell() != null) {
                if (!reachable3Cells.contains(this.getRightCell().getDownCell()))
                    reachable3Cells.add(this.getRightCell().getDownCell());
                if (this.getRightCell().getDownCell().getDownCell() != null && !reachable3Cells.contains(this.getRightCell().getDownCell().getDownCell()))
                    reachable3Cells.add(this.getRightCell().getDownCell().getDownCell());
                if (this.getRightCell().getDownCell().getRightCell() != null && !reachable3Cells.contains(this.getRightCell().getDownCell().getRightCell()))
                    reachable3Cells.add(this.getRightCell().getDownCell().getRightCell());
                if (this.getRightCell().getDownCell().getLeftCell() != null && !reachable3Cells.contains(this.getRightCell().getDownCell().getLeftCell()))
                    reachable3Cells.add(this.getRightCell().getDownCell().getLeftCell());
            }
            if (this.getRightCell().getRightCell() != null) {
                if (!reachable3Cells.contains(this.getRightCell().getRightCell()))
                    reachable3Cells.add(this.getRightCell().getRightCell());
                if (this.getRightCell().getRightCell().getUpCell() != null && !reachable3Cells.contains(this.getRightCell().getRightCell().getUpCell()))
                    reachable3Cells.add(this.getRightCell().getRightCell().getUpCell());
                if (this.getRightCell().getRightCell().getDownCell() != null && !reachable3Cells.contains(this.getRightCell().getRightCell().getDownCell()))
                    reachable3Cells.add(this.getRightCell().getRightCell().getDownCell());
                if (this.getRightCell().getRightCell().getRightCell() != null && !reachable3Cells.contains(this.getRightCell().getRightCell().getRightCell()))
                    reachable3Cells.add(this.getRightCell().getRightCell().getRightCell());
            }
            if (this.getRightCell().getUpCell() != null) {
                if (!reachable3Cells.contains(this.getRightCell().getUpCell()))
                    reachable3Cells.add(this.getRightCell().getUpCell());
                if (this.getRightCell().getUpCell().getUpCell() != null && !reachable3Cells.contains(this.getRightCell().getUpCell().getUpCell()))
                    reachable3Cells.add(this.getRightCell().getUpCell().getUpCell());
                if (this.getRightCell().getUpCell().getLeftCell() != null && !reachable3Cells.contains(this.getRightCell().getUpCell().getLeftCell()))
                    reachable3Cells.add(this.getRightCell().getUpCell().getLeftCell());
                if (this.getRightCell().getUpCell().getRightCell() != null && !reachable3Cells.contains(this.getRightCell().getUpCell().getRightCell()))
                    reachable3Cells.add(this.getRightCell().getUpCell().getRightCell());
            }
        }
    }

    /**
     * creates a list of the cell that are reachable in 2 moves from the actual 
     */
    public void initializeReachable2Cells(){
        if(this.getUpCell()!=null){
            reachable2Cells.add(this.getUpCell());
            if(!reachable2Cells.contains(this.getUpCell().getUpCell())&&this.getUpCell().getUpCell()!=null)
                reachable2Cells.add(this.getUpCell().getUpCell());
            if(!reachable2Cells.contains(this.getUpCell().getLeftCell())&&this.getUpCell().getLeftCell()!=null)
                reachable2Cells.add(this.getUpCell().getLeftCell());
            if(!reachable2Cells.contains(this.getUpCell().getRightCell())&&this.getUpCell().getRightCell()!=null)
                reachable2Cells.add(this.getUpCell().getRightCell());
        }
        if(this.getLeftCell()!=null){
            reachable2Cells.add(this.getLeftCell());
            if(!reachable2Cells.contains(this.getLeftCell().getUpCell())&&this.getLeftCell().getUpCell()!=null)
                reachable2Cells.add(this.getLeftCell().getUpCell());
            if(!reachable2Cells.contains(this.getLeftCell().getLeftCell())&&this.getLeftCell().getLeftCell()!=null)
                reachable2Cells.add(this.getLeftCell().getLeftCell());
            if(!reachable2Cells.contains(this.getLeftCell().getDownCell())&&this.getLeftCell().getDownCell()!=null)
                reachable2Cells.add(this.getLeftCell().getDownCell());
        }
        if(this.getDownCell()!=null){
            reachable2Cells.add(this.getDownCell());
            if(!reachable2Cells.contains(this.getDownCell().getDownCell())&&this.getDownCell().getDownCell()!=null)
                reachable2Cells.add(this.getDownCell().getDownCell());
            if(!reachable2Cells.contains(this.getDownCell().getLeftCell())&&this.getDownCell().getLeftCell()!=null)
                reachable2Cells.add(this.getDownCell().getLeftCell());
            if(!reachable2Cells.contains(this.getDownCell().getRightCell())&&this.getDownCell().getRightCell()!=null)
                reachable2Cells.add(this.getDownCell().getRightCell());
        }
        if(this.getRightCell()!=null){
            reachable2Cells.add(this.getRightCell());
            if(!reachable2Cells.contains(this.getRightCell().getUpCell())&&this.getRightCell().getUpCell()!=null)
                reachable2Cells.add(this.getRightCell().getUpCell());
            if(!reachable2Cells.contains(this.getRightCell().getRightCell())&&this.getRightCell().getRightCell()!=null)
                reachable2Cells.add(this.getRightCell().getRightCell());
            if(!reachable2Cells.contains(this.getRightCell().getDownCell())&&this.getRightCell().getDownCell()!=null)
                reachable2Cells.add(this.getRightCell().getDownCell());
        }
    }
}

