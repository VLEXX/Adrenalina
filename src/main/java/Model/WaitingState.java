package Model;

import java.io.PrintWriter;
import java.util.Scanner;

public class WaitingState implements State {

    @Override
    public void doAction(PrintWriter printWriter, Scanner scanner, InitializeAllPlay i, Player p) {
        String s = new String();
        s = scanner.nextLine();
        if(s.isEmpty()==false) {
            printWriter.println("NOT YOUR TURN!\nWAIT PLEASE...");
        }
    }
}
