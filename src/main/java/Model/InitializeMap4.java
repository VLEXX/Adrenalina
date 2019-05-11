//Author: Alex Saletti
package Model;

public class InitializeMap4 implements StrategyMap {
    @Override
    public Map initializeMap() {
        Map map4 = new Map();
        map4.setMapname("4");
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        Room room4 = new Room(4);
        Room room5 = new Room(5);
        Room room6 = new Room(6);
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        Cell cell3 = new Cell(3);
        Cell cell4 = new Cell(4);
        Cell cell5 = new Cell(5);
        Cell cell6 = new Cell(6);
        Cell cell7 = new Cell(7);
        Cell cell8 = new Cell(8);
        Cell cell9 = new Cell(9);
        Cell cell10 = new Cell(10);
        Cell cell11 = new Cell(11);
        Cell cell12 = new Cell(12);
        //stanze presenti nella mappa
        map4.addRoom(room1);
        map4.addRoom(room2);
        map4.addRoom(room3);
        map4.addRoom(room4);
        map4.addRoom(room5);
        map4.addRoom(room6);
        //celle presenti nelle stanze
        room1.addCellsList(cell1);
        room1.addCellsList(cell5);
        room2.addCellsList(cell2);
        room2.addCellsList(cell3);
        room3.addCellsList(cell4);
        room4.addCellsList(cell6);
        room5.addCellsList(cell7);
        room5.addCellsList(cell8);
        room5.addCellsList(cell11);
        room5.addCellsList(cell12);
        room6.addCellsList(cell9);
        room6.addCellsList(cell10);
        //settaggio celle
        cell1.setCells(null, cell5, null, cell2);
        cell2.setCells(null, cell6, cell1, cell3);
        cell3.setCells(null, cell7, cell2, cell4);
        cell4.setCells(null, cell8, cell3, null);
        cell5.setCells(cell1, cell9, null, null);
        cell6.setCells(cell2, cell10, null, null);
        cell7.setCells(cell3, cell11, null, cell8);
        cell8.setCells(cell4, cell12, cell7, null);
        cell9.setCells(cell5, null, null, cell10);
        cell10.setCells(cell6, null, cell9, cell11);
        cell11.setCells(cell7, null, cell10, cell12);
        cell12.setCells(cell8, null, cell11, null);
        //settaggio celle raggiungibili
        map4.getRoomList().forEach(room -> room.getCellsList().forEach(cell -> cell.initializeReachableCells()));
        return map4;
    }
}
