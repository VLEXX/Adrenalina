/**
 * @author Federico Scatà
 */
package View;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class InputStdin extends Thread {
    private Scanner stdin;

    public InputStdin(Scanner scanner){
        stdin=scanner;
    }

    public void run(){
        while(true){
            String s = stdin.nextLine();
        }
    }
}
