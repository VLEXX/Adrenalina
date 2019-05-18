/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.InitializeAllPlay;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.MessageString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServerManagerFunction {

    public ServerManagerFunction(){}

    public synchronized Player chooseCharacterManager(PrintWriter printWriter, ObjectInputStream objectInputStream, InitializeAllPlay allPlay, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject(allPlay.getCurrentDeckState());

        Player player;
        Boolean ok;
        while(true) {
            player = (Player) objectInputStream.readObject();
            if (allPlay.getCurrentDeckState().getPlayers().contains(player)) {
                MessageString message = new MessageString("You choose: " + player.toString());
                objectOutputStream.writeObject(message);
                allPlay.getCurrentPlayerState().put(player, new CurrentPlayerState(player));
                allPlay.getCurrentDeckState().getPlayers().remove(player);
                allPlay.getIdClientList().getPlayerArrayList().add(player);
                ok = true;
                objectOutputStream.writeObject(ok);
                System.out.println(allPlay.getPlayercountertemp());
                allPlay.decreasePlayerCounterTemp();
                System.out.println(allPlay.getPlayercountertemp());
                break;
            }
            else{
                MessageString message = new MessageString("Character not available");
                objectOutputStream.writeObject(message);
                ok = false;
                objectOutputStream.writeObject(ok);
            }
        }
        return player;
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
