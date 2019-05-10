/**
 * @author Federico Scatà
 */
package Model;

/**
 *
 */
public class DataPacket {
    private Cell cell;
    private Player player;
    private StatesEnum statesEnum;

    public DataPacket(){
        this.cell=null;
        this.player=null;
    }

    /**
     * @return
     */
    public Cell getCell() {
        return cell;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * @param cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public StatesEnum getStatesEnum() {
        return statesEnum;
    }

    public void setStatesEnum(StatesEnum statesEnum) {
        this.statesEnum = statesEnum;
    }
}
