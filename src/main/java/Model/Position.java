package Model;

public class Position {
    private Cell CurrentCell;
    private Room CurrentRoom;

    public Position(){
        this.CurrentCell=null;
        this.CurrentRoom=null;
    }

    public Cell getCurrentCell() {
        return CurrentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        CurrentCell = currentCell;
    }

    public Room getCurrentRoom() {
        return CurrentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        CurrentRoom = currentRoom;
    }
}
