/**
 * @author Federico Scatà
 */
package ServerController;

import model.gamedata.InitializeAllPlay;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartGame extends Thread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
    }

    public synchronized void run(){
        while(true){
            try {
                DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                MessageEnum messageEnum = allPlay.getPlayerState(player).doAction(dataPacket,allPlay);
                objectOutputStream.writeObject(messageEnum);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
