/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Thread that manage the game
 */
public class StartGame extends Thread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private HashMap<StatesEnum, model.modelstates.State> stateHashMap;
    private Updater updater;
    private IDClientList idClientList;

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo, HashMap<StatesEnum, model.modelstates.State> hashMap, Updater update, IDClientList clientList){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = hashMap;
        this.updater = update;
        this.idClientList=clientList;
    }

    public synchronized void run(){

        while(true){
            try {
                if(allPlay.isEndgame()==false) {
                    DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                    MessageEnum messageEnum = allPlay.getPlayerState(player).doAction(dataPacket);
                    objectOutputStream.writeObject(messageEnum);
                    if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.END)){
                        if(allPlay.getCurrentPlayerState().get(player).isEndturn()) {
                            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.WAIT));
                            if (idClientList.getIndexArray() < idClientList.getPlayerArrayList().size()-1) {
                                idClientList.increaseIndexArray();
                            }
                            else{
                                idClientList.resetIndexArray();
                            }
                            if (allPlay.getCurrentPlayerState().get(idClientList.getPlayerArrayList().get(idClientList.getIndexArray())).getPlayerposition().getCurrentcell() == null) {
                                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.SPAWN));
                            } else {
                                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.ACTION));
                            }
                            allPlay.getCurrentPlayerState().get(player).setEndturn(false);
                        }
                        if(allPlay.getSkullArray()[7]!=null && allPlay.getStateSelectedMode().getSelectedmode()== Mode.BASE) {
                            for(Player player: idClientList.getPlayerArrayList()){
                                allPlay.getPlayerStateTempFrenzy().put(player, allPlay.getHashMapState().get(player));
                                allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.FRENZY));
                            }
                        }
                    }
                    UpdatePacket updatePacket = updater.updateClient(player);

                    objectOutputStream.writeObject(updatePacket);
                }
                if(allPlay.isEndgame()==true) {
                    break;
                }
            } catch (IOException | CloneNotSupportedException e) {

            } catch (ClassNotFoundException e) {

            }
        }
    }

    public HashMap<StatesEnum, model.modelstates.State> getStateHashMap() {
        return stateHashMap;
    }
}
