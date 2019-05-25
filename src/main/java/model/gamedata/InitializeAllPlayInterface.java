package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InitializeAllPlayInterface extends Remote {

     int getPlayercountertemp() throws RemoteException;

     CurrentDeckState getCurrentDeckState() throws RemoteException;

     void addPlayerCounter() throws RemoteException;

}
