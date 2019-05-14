/**
 * @author Federico Scatà
 */
package view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputMessage extends Thread {
    private Scanner inMessage;

    public InputMessage(Scanner scanner){
        this.inMessage=scanner;
    }

    public void run(){
        while(true){
            try{
                String s = inMessage.nextLine();
                System.out.println(s);
            }
            catch (NoSuchElementException e){
            }
        }
    }
}
