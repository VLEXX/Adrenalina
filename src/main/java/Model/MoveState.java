//Author: Alex Saletti
package Model;

import java.io.PrintWriter;
import java.util.Scanner;

public class MoveState implements State {


    //metodo che gestisce lo spostamento del player p
    @Override
    public void doAction(PrintWriter printWriter, Scanner scanner, InitializeAllPlay i, Player p) {
        printWriter.println("Choose a cell...");
        String s = new String();
        while(true){                    //Server attende ID della cella in cui spostarsi dal client.
            if(s.isEmpty()) {
                s = scanner.nextLine();
            }
            else{
                break;
            }
        }
        Cell c = this.cellFinder(i,s);
        if(c==null) {
            printWriter.println("The selected cell doesn't exist!");
            return;
        }
        for(CurrentPlayerState ps : i.getCurrentPlayerState()) {
            if(ps.isActiveturn() && ps.getActiveplayer()==p){
                if (ps.getPlayerposition().getCurrentcell().getReachableCells().contains(c)){
                    ps.getPlayerposition().setCurrentcell(c);
                    i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> { if (room.getCellsList().contains(c)){ps.getPlayerposition().setCurrentroom(room);}});
                }
                else {
                    printWriter.println("Selected cell too far! Action denied!\n\nChoose another cell...\n");
                    return;
                }
            if(!ps.isActiveturn() && ps.getActiveplayer()==p){
                printWriter.println("NOT YOUR TURN!");
                return;
            }
            }
        }

    }


    //metodo che restituisce la cella avente un dato id
    public Cell cellFinder(InitializeAllPlay i, String id){
        int intid;

        try{
            intid=Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return null;
        }
        for(Room room : i.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell : room.getCellsList()){
                if (cell.getCellId()==intid)
                    return cell;
            }
        }
        return null;
    }
}

