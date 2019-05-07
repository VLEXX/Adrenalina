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
        Cell c;
        while(true){                    //Server attende ID della cella in cui spostarsi dal client.
            if(s.isEmpty()==true) {
                s = scanner.nextLine();
            }
            else{
                c = this.cellFinder(i,s,p);
                if(c!=null){
                    break;
                }
                else{
                    printWriter.println("Invalid cell! Please, insert another cell...\n");
                    s=null;
                }
            }

        }
        setMove(i, c, p);
    }

    public int setMove(InitializeAllPlay i, Cell c, Player p){
        for(CurrentPlayerState ps : i.getCurrentPlayerState()) {
            if(ps.getActiveplayer()==p){
                    ps.getPlayerposition().setCurrentcell(c);
                    i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> { if (room.getCellsList().contains(c)){ps.getPlayerposition().setCurrentroom(room);}});
                    return 0;
                }
        }
        return -1;
    }

    //metodo che restituisce la cella avente un dato id
    public Cell cellFinder(InitializeAllPlay i, String id, Player player){
        int intid;

        try{
            intid=Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return null;
        }

        for(Room room : i.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell : room.getCellsList()){
                if (cell.getCellId()==intid) {
                    if(cellIsReachable(i,player,cell)) {
                        return cell;
                    }
                    else
                        return null;
                }
            }
        }
        return null;
    }

    public boolean cellIsReachable(InitializeAllPlay i, Player p, Cell c){
        for(CurrentPlayerState cps : i.getCurrentPlayerState()){
            if (cps.getActiveplayer()==p && cps.getPlayerposition().getCurrentcell().getReachableCells().contains(c))
                return true;
        }
        return false;
    }
}

