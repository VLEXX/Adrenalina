/**
 * @author Federico Scatà
 */
package view;

import model.gamedata.CurrentDeckState;
import model.datapacket.MessageString;
import model.gamedata.Mode;
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
        if(s.equals("black")||s.equals("Black")||s.equals("BLACK")){
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

    public synchronized void manageChoice(Scanner stdin, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, ViewDatabase viewDatabase) throws IOException, ClassNotFoundException {
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
            if(ok==false){
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
    }

    public synchronized void manageVote(Scanner stdin, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
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

    }

    public String manageStart(Scanner stdin){
        System.out.println("> New Game\n> Continue\n");
        String s;
        while(true){
            s = stdin.nextLine();
            s.toLowerCase();
            if(s.equals("new game")||s.equals("continue")){
                return s;
            }
            else{
                System.out.println("WRONG INPUT! Please choose between 'New Game' or 'Continue'\n");
            }
        }
    }

    public String manageNickname(Scanner stdin) throws IOException, ClassNotFoundException {
        String s;
        System.out.print("Insert your nickname: \n");
        s = stdin.nextLine();
        return s;
    }

    public Mode manageMode(Scanner stdin){
        System.out.println("1) Base\n2) Domination\n");
        System.out.println("Vote mode (Insert '1' or '2'): ");
        String i;
        while(true){
            i = stdin.nextLine();
            if(i.equals("1")){
                return Mode.BASE;
            }
            else if (i.equals("2")){
                return Mode.DOMINATION;
            }
            else{
                System.out.println("WRONG INPUT! Please insert '1' for Base Mode or '2' for Domination Mode\n");
                System.out.println("Vote mode (Insert '1' or '2'): ");
            }
        }
    }

}
