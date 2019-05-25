package view;

import model.datapacket.MessageString;
import model.gamedata.CurrentDeckState;
import model.playerdata.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientManagerRMI {

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

    public Player manageChoice(Scanner stdin,  CurrentDeckState currentDeckState){
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


        Player player1;
        while (true) {
            player1 = this.chooseCharacter(stdin.nextLine());
            if(player1!=Player.FLAG) {
                return player1;
            }
            else{
                System.out.println("Character not available");
            }
        }
    }

    public synchronized int manageVote(Scanner stdin) {
        System.out.println("Vote Map: 1 | 2 | 3 | 4");
        String s;
        while(true) {
            s=stdin.nextLine();
            if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                Integer i = Integer.parseInt(s);
                return i;
            }
            else{
                System.out.println("WRONG INPUT\n\n Choose between 1 | 2 | 3 | 4");
            }
        }
    }
}
