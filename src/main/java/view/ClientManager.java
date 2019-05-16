/**
 * @author Federico Scatà
 */
package view;

import model.gamedata.CurrentDeckState;
import model.datapacket.MessageString;
import model.playerdata.Player;

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

    public synchronized void manageChoice(Scanner inMessage, Scanner stdin, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, ViewDatabase viewDatabase) throws IOException, ClassNotFoundException {
        CurrentDeckState currentDeckState= (CurrentDeckState)objectInputStream.readObject();
        System.out.println("Choose a character...");
        if(currentDeckState.getPlayers().size()==4){
            System.out.println("-------");
        }
        if(currentDeckState.getPlayers().size()==3){
            System.out.println("-------");
            System.out.println("-------");
        }
        if(currentDeckState.getPlayers().size()==2){
            System.out.println("-------");
            System.out.println("-------");
            System.out.println("-------");
        }
        if(currentDeckState.getPlayers().size()==1){
            System.out.println("-------");
            System.out.println("-------");
            System.out.println("-------");
            System.out.println("-------");
        }
        for(Player player: currentDeckState.getPlayers()) {
            System.out.println(player);
        }

        Boolean ok = false;
        Player player1 = null;
        while (true) {
            if(ok==false) {
                player1 = this.chooseCharacter(stdin.nextLine());
                objectOutputStream.writeObject(player1);
                System.out.println("\n");
                MessageString message = (MessageString)objectInputStream.readObject();
                System.out.println(message.getMessage());
                System.out.println("\n\n");
                ok = (Boolean) objectInputStream.readObject();
            }
            if(ok==true){
                viewDatabase.setThisplayer(player1);
                break;
            }
        }
        ok=false;
        System.out.println("Waiting the opponents...\n\n");
        while(true) {
            ok = (boolean) objectInputStream.readObject();
            if(ok == true){
                break;
            }
        }
    }

    public synchronized void manageVote(PrintWriter outMessage, Scanner inMessage, Scanner stdin, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        System.out.println("Vote Map: 1 | 2 | 3 | 4");
        String s;
        while(true) {
            s=stdin.nextLine();
            if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                Integer i = Integer.parseInt(s);
                objectOutputStream.writeObject(i);
                break;
            }
            else{
                System.out.println("WRONG INPUT\n\n Choose between 1 | 2 | 3 | 4");
            }
        }
        boolean ok = false;
        System.out.println("Waiting the opponents...\n\n");
        while(true) {
            ok = (boolean) objectInputStream.readObject();
            if(ok == true){
                break;
            }
        }
        MessageString message = (MessageString)objectInputStream.readObject();
        System.out.println(message.getMessage());
    }

}
