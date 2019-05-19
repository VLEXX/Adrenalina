/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
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

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo, HashMap<StatesEnum, model.modelstates.State> hashMap){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = hashMap;

    }

    public synchronized void run(){
        allPlay.putInHashMapState(player, StatesEnum.WAIT, stateHashMap);
        while(true){
            try {
                if(allPlay.isEndgame()==false) {
                    DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                    MessageEnum messageEnum = allPlay.getPlayerState(player).doAction(dataPacket);
                    objectOutputStream.writeObject(messageEnum);
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<StatesEnum, model.modelstates.State> getStateHashMap() {
        return stateHashMap;
    }
}
