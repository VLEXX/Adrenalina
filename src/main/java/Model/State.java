package Model;

import java.io.PrintWriter;
import java.util.Scanner;

public interface State {
    void doAction(PrintWriter printWriter, Scanner scanner, InitializeAllPlay i, Player p);
}
