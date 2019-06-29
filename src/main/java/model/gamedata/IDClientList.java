/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.Player;
import servercontroller.ObserverCounter;
import servercontroller.SubjectCounter;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//Classe che memorizza gli ID dei client che si collegano al server (da 3 a 5 client)
public class IDClientList extends UnicastRemoteObject implements Remote, IDClientListInterface, Serializable {
    private ArrayList<Integer> clientlist;   //Array che memorizza i token
    private ArrayList<Player> playerArrayList;
    private int indexArray;
    private int clientCounter;
    private ArrayList<ObserverCounter> observerCounters;
    private ArrayList<String> nicknameList;
    private HashMap<String, Player> nickPlayer;

    //Costruttore che inizializza l'array a 0 (gli ID assegnati sono diversi da 0)
    public IDClientList() throws RemoteException{
        this.clientlist = new ArrayList<>();
        this.playerArrayList = new ArrayList<>();
        this.indexArray=0;
        this.clientCounter=5;
        this.observerCounters=new ArrayList<>();
        this.nicknameList = new ArrayList<>();
        this.nickPlayer=new HashMap<>();
    }

    public synchronized HashMap<String, Player> getNickPlayer() {
        return nickPlayer;
    }

    public synchronized ArrayList<String> getNicknameList() {
        return nicknameList;
    }

    public synchronized ArrayList<Player> getPlayerArrayList() throws RemoteException {
        return playerArrayList;
    }

    public synchronized int getIndexArray() {
        return indexArray;
    }

    public void increaseIndexArray(){
        this.indexArray = this.indexArray + 1;
    }

    public void resetIndexArray(){
        this.indexArray = 0;
    }

    //Ritorna l'array degli ID Client
    public ArrayList<Integer> getClientlist() throws RemoteException {
        return this.clientlist;
    }

    //Setta nella cella corretta, a seconda dell'indice passato, l'ID da memorizzare
    public synchronized int addClient() throws RemoteException {
        Random random = new Random();
        int i;
        while (true) {
            i = random.nextInt(1000);
            if (!(clientlist.contains(i))) {
                this.clientlist.add(i);
                update();
                break;
            }
        }
        return i;
    }

    public synchronized int getClientCounter() throws RemoteException {
        return this.clientCounter;
    }

    public synchronized void update() throws RemoteException{
        this.clientCounter--;
    }
}
