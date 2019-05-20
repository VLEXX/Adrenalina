/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.modelstates.ActionState;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class StartGame extends Thread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private HashMap<StatesEnum, model.modelstates.State> stateHashMap;
    private UpdateThread updateThread;

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo, HashMap<StatesEnum, model.modelstates.State> hashMap, UpdateThread update){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = hashMap;
        this.updateThread = update;
    }

    public synchronized void run(){

        while(true){
            try {
                if(allPlay.isEndgame()==false) {
                    DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                    MessageEnum messageEnum = allPlay.getPlayerState(player).doAction(dataPacket);
                    objectOutputStream.writeObject(messageEnum);
                    updateThread.updateClient();
                }
                if(allPlay.isEndgame()==true) {
                    break;
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            }
        }
    }

    public HashMap<StatesEnum, model.modelstates.State> getStateHashMap() {
        return stateHashMap;
    }
}
