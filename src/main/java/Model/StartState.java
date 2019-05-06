package Model;

import java.io.PrintWriter;
import java.util.Scanner;

public class StartState implements State {

    @Override
    public void doAction(PrintWriter printWriter, Scanner scanner, InitializeAllPlay i, Player p) {
        //TODO
        //1 - Invia al client richiesta di input (RACCOGLI|SPARA|MUOVITI)
        //2 - Ricevi dal client nuova azione (RACCOGLI|SPARA|MUOVITI)
        //3 - A seconda dell'azione ricevuta in input cambia stato
    }
}
