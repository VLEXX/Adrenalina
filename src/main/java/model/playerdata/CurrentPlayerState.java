/**
 * @author Federico Scatà
 */
package model.playerdata;

import model.map.Position;

import java.io.*;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Class with player current state
 */
public class CurrentPlayerState implements Serializable {
    private Position playerposition;    //Posizione attuale del giocatore
    private boolean activeturn;         //"true" se è il proprio turno "false" altrimenti
    private PlayerBoard board;          //PlanciaGiocatore
    private int actioncounter;          //Contatore di azioni rimanenti che può compiere il giocatore (max 2 per turno)
    private Player activeplayer;        //Memorizza il colore/personaggio scelto dal giocatore
    private HashMap<Player, Integer> controlMarks;  //Controlla il numero dei marchi da aggiungere alla plancia danni giocatore
    private Player hit;
    private boolean endturn;
    private int token;
    private boolean attackinprogress;

    /**
     * Constructor
     */
    public CurrentPlayerState(Player player) {
        this.playerposition = new Position();
        this.activeturn = false;
        this.board = new PlayerBoard();
        this.actioncounter = 2;
        this.activeplayer = player;
        this.controlMarks = new HashMap<>();
        this.hit = null;
        this.endturn=false;
        this.attackinprogress=false;
    }

    public boolean isAttackinprogress() {
        return attackinprogress;
    }

    public void setAttackinprogress(boolean attackinprogress) {
        this.attackinprogress = attackinprogress;
    }

    public int getToken() {
        return token;
    }

    public synchronized void setToken(int token){
        this.token = token;
    }

    public synchronized void setEndturn(boolean endturn) {
        this.endturn = endturn;
    }

    public boolean isEndturn() {
        return endturn;
    }

    public Player getHit() {
        return hit;
    }

    public synchronized void setHit(Player hit) {
        this.hit = hit;
    }

    /**
     * @return player position
     */
    public Position getPlayerposition() {
        return this.playerposition;
    }

    /**
     * Set player position
     *
     * @param playerposition
     */
    public synchronized void setPlayerposition(Position playerposition) {
        this.playerposition = playerposition;
    }

    /**
     * @return boolean activeturn
     */
    public boolean isActiveturn() {
        return this.activeturn;
    }

    /**
     * Get boolean activeturn
     *
     * @param activeturn
     */
    public synchronized void setActiveturn(boolean activeturn) {
        this.activeturn = activeturn;
    }

    /**
     * @return board
     */
    public PlayerBoard getBoard() {
        return this.board;
    }

    /**
     * Set board
     *
     * @param board
     */
    public synchronized void setBoard(PlayerBoard board) {
        this.board = board;
    }

    /**
     * @return int actioncounter
     */
    public int getActioncounter() {
        return this.actioncounter;
    }

    /**
     * Reset actioncounter = 2
     */
    public void resetActionCounter() {
        this.actioncounter = 2;
    }

    /**
     * Decreases actioncounter -1
     */
    public void decreaseActionCounter() {
        this.actioncounter = actioncounter - 1;
    }

    /**
     * @return activeplayer
     */
    public Player getActiveplayer() {
        return this.activeplayer;
    }

    /**
     * Set Player activeplayer
     *
     * @param activeplayer
     */
    public synchronized void setActiveplayer(Player activeplayer) {
        this.activeplayer = activeplayer;
    }

    public HashMap<Player, Integer> getControlMarks() {
        return controlMarks;
    }

    public void addControlMarks(Player player, int i) {
        this.controlMarks.put(player, i);
    }

    public CurrentPlayerState deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(this);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return (CurrentPlayerState) objectInputStream.readObject();
    }
}
