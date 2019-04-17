package View;

import Model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public void manageChoice(Scanner inMessage, Scanner stdin, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        System.out.println(inMessage.nextLine());
        for(int i = 0; i<5; i++) {
            System.out.println(inMessage.nextLine());
        }
        Boolean ok = false;
        while (true) {
            if(ok==false) {
                Player player = this.chooseCharacter(stdin.nextLine());
                objectOutputStream.writeObject(player);
                System.out.println(inMessage.nextLine());
                ok = (Boolean) objectInputStream.readObject();
            }
            if(ok==true){
                break;
            }
        }
    }

}
