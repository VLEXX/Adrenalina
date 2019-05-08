package Model;

public class DataPacket {
    private Cell cell;
    private Player player;

    public DataPacket(){
        this.cell=null;
        this.player=null;
    }

    public Cell getCell() {
        return cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
