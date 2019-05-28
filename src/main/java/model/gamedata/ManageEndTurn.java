package model.gamedata;

import model.datapacket.StatesEnum;
import model.modelstates.EndTurnState;
import model.modelstates.State;
import model.playerdata.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ManageEndTurn extends UnicastRemoteObject implements ManageEndTurnInterface {

    private InitializeAllPlay allPlay;
    private IDClientList idClientList;

    public ManageEndTurn(InitializeAllPlay allPlay, IDClientList idClientList)throws RemoteException{
            this.idClientList=idClientList;
            this.allPlay=allPlay;
    }

    public void manageEndTurn(Player player, HashMap<StatesEnum, State> hashMap) throws RemoteException {
        if(allPlay.getCurrentPlayerState().get(player).isEndturn()==true) {
            allPlay.getHashMapState().replace(player, hashMap.get(StatesEnum.WAIT));
            if (idClientList.getIndexArray() < idClientList.getPlayerArrayList().size()-1) {
                idClientList.increaseIndexArray();
            }
            else{
                idClientList.resetIndexArray();
            }
            if (allPlay.getCurrentPlayerState().get(idClientList.getPlayerArrayList().get(idClientList.getIndexArray())).getPlayerposition().getCurrentcell() == null) {
                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), hashMap.get(StatesEnum.SPAWN));
            } else {
                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), hashMap.get(StatesEnum.ACTION));
            }
            allPlay.getCurrentPlayerState().get(player).setEndturn(false);
        }
    }
}
