package View;

import Model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientManager {

    public ClientManager(){

    }

    public Player chooseCharacter(String s){
        if(s.equals("black")||s.equals("Black")||s.equals(" BLACK")){
            return Player.BLACK;
        }
        if(s.equals("blue")||s.equals("Blue")||s.equals("BLUE")){
            return Player.BLUE;
        }
        if(s.equals("green")||s.equals("Green")||s.equals("GREEN")){
            return Player.GREEN;
        }
        if(s.equals("purple")||s.equals("Purple")||s.equals("PURPLE")){
            return Player.PURPLE;
        }
        if(s.equals("yellow")||s.equals("Yellow")||s.equals("YELLOW")){
            return Player.YELLOW;
        }
        else{
            return Player.FLAG;
        }
    }

    public synchronized void manageChoice(Scanner inMessage, Scanner stdin, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        System.out.println(inMessage.nextLine());
        for(int i = 0; i<5; i++) {
            System.out.println(inMessage.nextLine());
        }
        Boolean ok = false;
        while (true) {
            if(ok==false) {
                Player player = this.chooseCharacter(stdin.nextLine());
                objectOutputStream.writeObject(player);
                System.out.println("\n");
                System.out.println(inMessage.nextLine());
                System.out.println("\n\n");
                ok = (Boolean) objectInputStream.readObject();
            }
            if(ok==true){
                break;
            }
        }
        System.out.println("Waiting the opponents...\n\n");
    }

    public synchronized void manageVote(PrintWriter outMessage, Scanner inMessage, Scanner stdin){
        System.out.println(inMessage.nextLine());

        String s;
        while(true) {
            s=stdin.nextLine();
            if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                outMessage.println(s);
                outMessage.flush();
                System.out.println("\n\n");
                break;
            }
            else{
                System.out.println("WRONG INPUT\n\n Choose between 1 | 2 | 3 | 4");
            }
        }
        System.out.println("Waiting the opponents...\n\n");
    }

}
