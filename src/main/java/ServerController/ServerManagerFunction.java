package ServerController;

import Model.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ServerManagerFunction {

    public ServerManagerFunction(){}

    public synchronized void chooseCharacterManager(PrintWriter printWriter, ObjectInputStream objectInputStream, InitializeAllPlay allPlay, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject(allPlay.getCurrentDeckState());

        Player player;
        Boolean ok;
        while(true) {
            player = (Player) objectInputStream.readObject();
            if (allPlay.getCurrentDeckState().getPlayers().contains(player)) {
                Message message = new Message("You choose: " + player.toString());
                objectOutputStream.writeObject(message);
                allPlay.getCurrentPlayerState().put(player, new CurrentPlayerState(player));
                allPlay.getCurrentDeckState().getPlayers().remove(player);
                ok = true;
                objectOutputStream.writeObject(ok);
                System.out.println(allPlay.getPlayercountertemp());
                allPlay.decreasePlayerCounterTemp();
                System.out.println(allPlay.getPlayercountertemp());
                break;
            }
            else{
                Message message = new Message("Character not available");
                objectOutputStream.writeObject(message);
                ok = false;
                objectOutputStream.writeObject(ok);
            }
        }
    }

    public synchronized void manageVoteMap(Scanner inMessage, InitializeAllPlay allPlay, PrintWriter printWriter, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        Integer i = (Integer) objectInputStream.readObject();

        if(i==1){
            allPlay.getVoteMap().setVoteresult(0);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
        if(i==2){
            allPlay.getVoteMap().setVoteresult(1);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
        if(i==3){
            allPlay.getVoteMap().setVoteresult(2);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
        if(i==4){
            allPlay.getVoteMap().setVoteresult(3);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
    }
}
