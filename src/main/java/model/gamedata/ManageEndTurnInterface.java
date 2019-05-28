package model.gamedata;

import model.datapacket.StatesEnum;
import model.modelstates.State;
import model.playerdata.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface ManageEndTurnInterface extends Remote {

     void manageEndTurn(Player player, HashMap<StatesEnum, State> hashMap) throws RemoteException;

}
