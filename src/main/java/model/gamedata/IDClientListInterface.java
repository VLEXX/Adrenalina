package model.gamedata;

import model.playerdata.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDClientListInterface extends Remote {

    ArrayList<Player> getPlayerArrayList() throws RemoteException;

    int addClient() throws RemoteException;

    HashMap<Player, Boolean> getConnection() throws RemoteException;

    ArrayList<Player> getPlayerRMI() throws RemoteException;

    void addConnection(Player player)throws RemoteException;

    void replaceConnection(Player player, Boolean bool) throws RemoteException;

    void addPlayerRMI(Player player) throws RemoteException;

     boolean isInPlayerList(String nick) throws RemoteException;

    void addPlayerInList (String nick) throws RemoteException;

    Player getPlayerFromNick(String nick) throws RemoteException;

    void setSleepPlayer(Player sleepPlayer) throws RemoteException;

    Integer getPlayerNick(Player player) throws RemoteException;

    void putPlayerToken(Player player, Integer token) throws RemoteException;

    void removePlayerToken(Player player) throws RemoteException;

}
