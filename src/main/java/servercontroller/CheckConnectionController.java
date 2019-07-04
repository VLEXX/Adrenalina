/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.modelstates.State;
import model.playerdata.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Check every 600 ms if each RMI client is connected.
 */
public class CheckConnectionController extends Thread {

    private IDClientList idClientList;
    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, model.modelstates.State> stateHashMap;

    public CheckConnectionController(IDClientList clientList, InitializeAllPlay allPlay, HashMap<StatesEnum, model.modelstates.State> hashMap){
        this.idClientList=clientList;
        this.allPlay=allPlay;
        this.stateHashMap=hashMap;
    }

    @Override
    public void run() {
        try{
            ArrayList<Player> players = new ArrayList<>();
            while(true){
                if(allPlay.isEndgame()){
                    break;
                }
                else{
                    for(Player player: idClientList.getPlayerRMI()){
                        if(idClientList.getSleepPlayer()!=null){
                            if(!player.equals(idClientList.getSleepPlayer())){
                                if (idClientList.getConnection().get(player).equals(true)) {
                                    idClientList.getConnection().replace(player, false);
                                } else {
                                    if(allPlay.isStarting()) {
                                        if (!allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.WAIT)) {
                                            allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.WAIT));
                                            if (idClientList.getIndexArray() < idClientList.getPlayerArrayList().size() - 1) {
                                                idClientList.increaseIndexArray();
                                            } else {
                                                idClientList.resetIndexArray();
                                            }
                                            if (allPlay.getCurrentPlayerState().get(idClientList.getPlayerArrayList().get(idClientList.getIndexArray())).getPlayerposition().getCurrentcell() == null) {
                                                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.SPAWN));
                                            } else {
                                                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.ACTION));
                                            }
                                            allPlay.getCurrentPlayerState().get(player).setEndturn(false);
                                        }
                                    }
                                    idClientList.getConnection().remove(player);
                                    players.add(player);
                                    idClientList.getPlayerArrayList().remove(player);
                                    idClientList.getClientlist().remove(idClientList.getPlayerNick(player));
                                }
                            }
                        }
                        else{
                            if (idClientList.getConnection().get(player).equals(true)) {
                                idClientList.getConnection().replace(player, false);
                            } else {
                                if(allPlay.isStarting()) {
                                    if (!allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.WAIT)) {
                                        allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.WAIT));
                                        if (idClientList.getIndexArray() < idClientList.getPlayerArrayList().size() - 1) {
                                            idClientList.increaseIndexArray();
                                        } else {
                                            idClientList.resetIndexArray();
                                        }
                                        if (allPlay.getCurrentPlayerState().get(idClientList.getPlayerArrayList().get(idClientList.getIndexArray())).getPlayerposition().getCurrentcell() == null) {
                                            allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.SPAWN));
                                        } else {
                                            allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.ACTION));
                                        }
                                        allPlay.getCurrentPlayerState().get(player).setEndturn(false);
                                    }
                                }
                                idClientList.getConnection().remove(player);
                                players.add(player);
                                idClientList.getPlayerArrayList().remove(player);
                                idClientList.getClientlist().remove(idClientList.getPlayerNick(player));
                            }
                        }
                    }
                    if(players.size()!=0){
                        for(Player player: players){
                            idClientList.getPlayerRMI().remove(player);
                        }
                    }
                    players.clear();
                }
                sleep(600);
            }
        } catch (RemoteException | InterruptedException e) {

        }
    }
}
