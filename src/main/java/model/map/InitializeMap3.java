/**
 * @author Alex Saletti
 */
package model.map;

import model.munitions.Munitions;

import java.io.Serializable;

public class InitializeMap3 implements StrategyMap, Serializable {

    /**
     * Initialize map 3
     * @return map3
     */
    @Override
    public Map initializeMap() {
        //creazione delle istanze mappa2, stanze e celle
        Map map3 = new Map();
        map3.setMapname("3");
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        Room room4 = new Room(4);
        //creazione delle celle
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
        SpawnPoint spawnPointRed = new SpawnPoint(Munitions.RED);
        SpawnPoint spawnPointBlue = new SpawnPoint(Munitions.BLUE);
        SpawnPoint spawnPointYellow = new SpawnPoint(Munitions.YELLOW);
        cell3.setSpawnpointzone(spawnPointBlue);
        cell4.setSpawnpointzone(spawnPointRed);
        cell10.setSpawnpointzone(spawnPointYellow);
        //stanze presenti nella mappa
        map3.addRoom(room1);
        map3.addRoom(room2);
        map3.addRoom(room3);
        map3.addRoom(room4);
        //celle presenti nelle stanze
        room1.addCellsList(cell1);
        room1.addCellsList(cell2);
        room1.addCellsList(cell3);
        room2.addCellsList(cell4);
        room2.addCellsList(cell5);
        room2.addCellsList(cell6);
        room3.addCellsList(cell7);
        room3.addCellsList(cell10);
        room4.addCellsList(cell8);
        room4.addCellsList(cell9);
        //settaggio celle
        cell1.setCells(null, cell4, null, cell2);
        cell2.setCells(null, null, cell1, cell3);
        cell3.setCells(null, cell6, cell2, null);
        cell4.setCells(cell1, null, null, cell5);
        cell5.setCells(null, cell8, cell4, cell6);
        cell6.setCells(cell3, null, cell5, cell7);
        cell7.setCells(null, cell10, cell6, null);
        cell8.setCells(cell5, null, null, cell9);
        cell9.setCells(null, null, cell8, cell10);
        cell10.setCells(cell7, null, cell9, null);
        //settaggio celle raggiungibili
        for(Room r : map3.getRoomList()) {
            for (Cell c : r.getCellsList()) {
                c.initializeReachable2Cells();
                c.initializeReachable3Cells();
                c.initializeVisibleCells(map3);
            }
        }
        return map3;
    }
}
