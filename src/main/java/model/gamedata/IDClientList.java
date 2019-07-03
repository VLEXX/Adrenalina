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
    private HashMap<Player, Boolean> connection;
    private ArrayList<Player> playerRMI;
    private Player sleepPlayer;
    private HashMap<String, Integer> nickToken;
    private HashMap<Player, Integer> playerToken;

    //Costruttore che inizializza l'array a 0 (gli ID assegnati sono diversi da 0)
    public IDClientList() throws RemoteException{
        this.clientlist = new ArrayList<>();
        this.playerArrayList = new ArrayList<>();
        this.indexArray=0;
        this.clientCounter=5;
        this.observerCounters=new ArrayList<>();
        this.nicknameList = new ArrayList<>();
        this.nickPlayer=new HashMap<>();
        this.connection=new HashMap<>();
        this.playerRMI=new ArrayList<>();
        this.sleepPlayer=null;
        this.nickToken=new HashMap<>();
        this.playerToken=new HashMap<>();
    }

    public synchronized Integer getPlayerNick(Player player) throws RemoteException{
        return this.playerToken.get(player);
    }

    public synchronized void putPlayerToken(Player player, Integer token) throws RemoteException{
        this.playerToken.put(player, token);
    }

    public synchronized void removePlayerToken(Player player) throws RemoteException{
        this.playerToken.remove(player);
    }

    public synchronized void setSleepPlayer(Player sleepPlayer) throws RemoteException {
        this.sleepPlayer = sleepPlayer;
    }

    public Player getSleepPlayer() {
        return sleepPlayer;
    }

    public boolean isInPlayerList(String nick) throws RemoteException{
        if(playerArrayList.contains(nickPlayer.get(nick))){
            return true;
        }
        else{
            return false;
        }
    }

    public synchronized Player getPlayerFromNick(String nick) throws RemoteException{
        return nickPlayer.get(nick);
    }

    public synchronized void addNickPlayer(String nick, Player player){
        this.nickPlayer.put(nick, player);
    }

    public synchronized void addPlayerRMI(Player player) throws RemoteException{
        playerRMI.add(player);
    }

    public synchronized void addPlayerInList (String nick) throws RemoteException{
        playerArrayList.add(nickPlayer.get(nick));
    }

    public synchronized void addConnection(Player player) throws RemoteException{
        connection.put(player, true);
    }

    public synchronized void replaceConnection(Player player, Boolean bool) throws RemoteException{
        connection.replace(player, bool);
    }

    public synchronized ArrayList<Player> getPlayerRMI() throws RemoteException {
        return playerRMI;
    }

    public synchronized HashMap<Player, Boolean> getConnection() throws RemoteException{
        return connection;
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

    public synchronized void update() throws RemoteException{
        this.clientCounter--;
    }
}
