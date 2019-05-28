package model.gamedata;

import model.playerdata.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDClientListInterface extends Remote {

    ArrayList<Player> getPlayerArrayList() throws RemoteException;

    int addClient() throws RemoteException;

    void update() throws RemoteException;


}
