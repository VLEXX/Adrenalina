package view.viewstates;

import model.map.Cell;
import model.datapacket.DataPacket;
import model.playerdata.Player;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

/**
 * Move State: create and set the DataPacket with target Cell and Player
 */
public class ViewMoveState implements ViewState {

    /**
     * ask with System.in the target cell id and call setMovePacket method
     *
     * @param stdin Scanner
     * @param player Player to set in DataPacket
     * @param viewDatabase object with reachable cells
     * @return
     */
    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        System.out.println("Where do you want to move?\nWrite the cell ID between:\n");
        for(Cell cell: viewDatabase.getViewPlayerPosition().getCurrentcell().getReachable3Cells()) {
            System.out.print(cell.getCellId() + " | ");
        }
        System.out.println(" ");
        int id;
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        while(true){
            id=stdin.nextInt();
            stdin.nextLine();
            for(Cell cell: viewDatabase.getViewPlayerPosition().getCurrentcell().getReachable3Cells()){
                if(id==cell.getCellId()){
                    dataPacket.setPlayer(player);
                    dataPacket.setCell(cell);
                    return dataPacket;
                }
            }
            System.out.println("WRONG INPUT!\nPlease choose between:\n");
            for(Cell cell: viewDatabase.getViewPlayerPosition().getCurrentcell().getReachable3Cells()) {
                System.out.print(cell.getCellId() + " | ");
            }
        }
    }
}
