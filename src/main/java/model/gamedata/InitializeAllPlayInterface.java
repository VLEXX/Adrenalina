package model.gamedata;

import model.datapacket.StatesEnum;
import model.modelstates.State;
import model.playerdata.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface InitializeAllPlayInterface extends Remote {

     int getPlayercountertemp() throws RemoteException;

     CurrentDeckState getCurrentDeckState() throws RemoteException;

     void addPlayerCounter() throws RemoteException;

     void putInHashMapState(Player player, StatesEnum statesEnum, HashMap<StatesEnum, State> hashMap) throws RemoteException;

     HashMap<Player, State> getHashMapState() throws RemoteException;


}
