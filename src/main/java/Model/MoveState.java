//Author: Alex Saletti
package Model;

import java.io.PrintWriter;
import java.util.Scanner;

public class MoveState implements State {

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
        //Qui ci va un metodo per prelevare la cella corrispondente all'ID ricevuto dal client.
        Cell c; //Qui bisogna inserire la cella prelevata Cell c = metodo(id);
        i.getCurrentPlayerState().forEach(ps -> {
            if(ps.isActiveturn() && ps.getActiveplayer()==p){
                if (ps.getPlayerposition().getCurrentcell().getReachableCells().contains(c)){
                    ps.getPlayerposition().setCurrentcell(c);
                    i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> { if (room.getCellsList().contains(c)){ps.getPlayerposition().setCurrentroom(room);}});
                }
                else {
                    printWriter.println("Selected cell too far! Action denied!\n\nChose another cell...\n");
                }
            if(!ps.isActiveturn() && ps.getActiveplayer()==p){
                printWriter.println("NOT YOUR TURN!");
            }
            }
        });

    }
}

