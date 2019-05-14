//Author: Alex Saletti
package model.map;

public class InitializeMap2 implements StrategyMap {
    @Override
    public Map initializeMap() {
        //creazione delle istanze mappa2, stanze e celle
        Map map2 = new Map();
        map2.setMapname("2");
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        Room room4 = new Room(4);
        Room room5 = new Room(5);
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
        //stanze presenti nella mappa
        map2.addRoom(room1);
        map2.addRoom(room2);
        map2.addRoom(room3);
        map2.addRoom(room4);
        map2.addRoom(room5);
        //celle presenti nelle stanze
        room1.addCellsList(cell1);
        room1.addCellsList(cell4);
        room2.addCellsList(cell2);
        room2.addCellsList(cell3);
        room3.addCellsList(cell5);
        room3.addCellsList(cell6);
        room4.addCellsList(cell7);
        room4.addCellsList(cell11);
        room5.addCellsList(cell8);
        room5.addCellsList(cell9);
        room5.addCellsList(cell10);
        //settaggio celle
        cell1.setCells(null, cell4, null, cell2);
        cell2.setCells(null, cell5, cell1, cell3);
        cell3.setCells(null, cell6, cell2, null);
        cell4.setCells(cell1, cell8, null, null);
        cell5.setCells(cell2, cell9, null, cell6);
        cell6.setCells(cell3, null, cell5, cell7);
        cell7.setCells(null, cell11, cell6, null);
        cell8.setCells(cell4, null, null, cell9);
        cell9.setCells(cell5, null, cell8, cell10);
        cell10.setCells(null, null, cell9, cell11);
        cell11.setCells(cell7, null, cell10, null);
        //settaggio celle raggiungibili
        map2.getRoomList().forEach(room -> room.getCellsList().forEach(cell -> cell.initializeReachableCells()));
        return map2;
    }
}
