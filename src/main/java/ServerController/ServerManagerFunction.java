package ServerController;

import Model.CurrentDeckState;
import Model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class ServerManagerFunction {

    public ServerManagerFunction(){}

    public void chooseCharacterManager(PrintWriter printWriter, ObjectInputStream objectInputStream, CurrentDeckState deckState, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        printWriter.println("Choose a character...");
        printWriter.flush();
        if(deckState.getPlayers().size()==4){
            printWriter.println("-------");
        }
        if(deckState.getPlayers().size()==3){
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
        }
        if(deckState.getPlayers().size()==2){
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
        }
        if(deckState.getPlayers().size()==1){
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
        }
        for(Player player: deckState.getPlayers()) {
            printWriter.println(player);
            printWriter.flush();
        }
        Player player;
        Boolean ok;
        while(true) {
            player = (Player) objectInputStream.readObject();
            System.out.println(player.toString());
            if (deckState.getPlayers().contains(player)) {
                System.out.println("ciao");
                printWriter.println("You choose: " + player.toString());
                printWriter.flush();
                deckState.getPlayers().remove(player);
                ok = true;
                objectOutputStream.writeObject(ok);
                printWriter.flush();
                break;
            }
            else{
                printWriter.println("Character not available");
                printWriter.flush();
                ok = false;
                objectOutputStream.writeObject(ok);
                System.out.println("ciaociao");
            }
        }
    }
}
