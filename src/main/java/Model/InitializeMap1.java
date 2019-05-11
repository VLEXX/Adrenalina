//Author: Alex Saletti
package Model;

public class InitializeMap1 implements StrategyMap {

    @Override
    public Map initializeMap() {
        //creazione delle istanze mappa1, stanze e celle
        Map map1 = new Map();
        map1.setMapname("1");
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
        map1.addRoom(room1);
        map1.addRoom(room2);
        map1.addRoom(room3);
        map1.addRoom(room4);
        map1.addRoom(room5);
        //celle presenti nelle stanze
        room1.addCellsList(cell1);
        room1.addCellsList(cell2);
        room1.addCellsList(cell3);
        room2.addCellsList(cell4);
        room3.addCellsList(cell5);
        room3.addCellsList(cell6);
        room4.addCellsList(cell7);
        room4.addCellsList(cell8);
        room4.addCellsList(cell10);
        room4.addCellsList(cell11);
        room5.addCellsList(cell9);
        //settaggio porte
        cell1.setCells(null, cell5, null, cell2);
        cell2.setCells(null, null, cell1, cell3);
        cell3.setCells(null, cell7, cell2, cell4);
        cell4.setCells(null, cell8, cell3, null);
        cell5.setCells(cell1, null, null, cell6);
        cell6.setCells(null, cell9, cell5, null);
        cell7.setCells(cell3, cell10, null, cell8);
        cell8.setCells(cell4, cell11, cell7, null);
        cell9.setCells(cell6, null, null, cell10);
        cell10.setCells(cell7, null, cell9, cell11);
        cell11.setCells(cell8, null, cell10, null);
        //settaggio celle raggiungibili
        map1.getRoomList().forEach(room -> room.getCellsList().forEach(cell -> cell.initializeReachableCells()));
        return map1;
    }
}
