package ServerController;

import Model.CurrentPlayerState;
import Model.InizializeAllPlay;
import Model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ServerManagerFunction {

    public ServerManagerFunction(){}

    public synchronized void chooseCharacterManager(PrintWriter printWriter, ObjectInputStream objectInputStream, InizializeAllPlay allPlay, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        printWriter.println("Choose a character...");
        printWriter.flush();
        if(allPlay.getCurrentDeckState().getPlayers().size()==4){
            printWriter.println("-------");
        }
        if(allPlay.getCurrentDeckState().getPlayers().size()==3){
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
        }
        if(allPlay.getCurrentDeckState().getPlayers().size()==2){
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
        }
        if(allPlay.getCurrentDeckState().getPlayers().size()==1){
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
            printWriter.println("-------");
            printWriter.flush();
        }
        for(Player player: allPlay.getCurrentDeckState().getPlayers()) {
            printWriter.println(player);
            printWriter.flush();
        }
        Player player;
        Boolean ok;
        while(true) {
            player = (Player) objectInputStream.readObject();
            if (allPlay.getCurrentDeckState().getPlayers().contains(player)) {
                printWriter.println("You choose: " + player.toString());
                printWriter.flush();
                allPlay.getCurrentPlayerState().add(new CurrentPlayerState(player));
                allPlay.getCurrentDeckState().getPlayers().remove(player);
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
            }
        }
    }

    public synchronized void manageVoteMap(Scanner inMessage, InizializeAllPlay allPlay) throws RemoteException {
        String s = inMessage.nextLine();
        if(s.equals("1")){
            allPlay.getVoteMap().setVoteresult(0);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
        if(s.equals("2")){
            allPlay.getVoteMap().setVoteresult(1);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
        if(s.equals("3")){
            allPlay.getVoteMap().setVoteresult(2);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
            }
        }
        if(s.equals("4")){
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
