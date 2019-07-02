/**
 * @author Federico Scatà
 */
package model.gamedata;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Classe che gestisce la votazione per la scelta della mappa in cui giocare
public class VoteMap extends UnicastRemoteObject implements VoteMapInterface, Serializable {
    private int[] voteresult;   //Array che memorizza i voti ottenuti da ciascuna mappa (voteresult[0] => Mappa1, voteresult[1] => Mappa2, ...)
    private int finalresult;    //Memorizza la mappa finale votata (da 1 a 4)
    private int i;
    private boolean initmap;

    //Costruttore che inizialzza l'array a 0 e la mappa finale votata a -1 (le mappe vanno da 1 a 4)
    public VoteMap() throws RemoteException{
        voteresult = new int[]{0, 0, 0, 0};
        finalresult = -1;
        i = 0;
        initmap = false;
    }

    //Ritorna la mappa votata finala
    public synchronized int getFinalresult() throws RemoteException {
        return finalresult;
    }

    //Setta la mappa votata finale
    public synchronized void setFinalresult() {
        if (initmap == true) {
            int k = -1;
            for (int j = 0; j < 4; j++) {
                if (k < voteresult[j]) {
                    finalresult = j;
                    k = voteresult[j];
                }
            }
        }
    }

    //Ritorna l'array dei voti
    public synchronized int[] getVoteresult() {
        return voteresult;
    }

    //Setta il voto a seconda dell'indice(che corrisponde alla mappa votata, 0,1,2,3)
    public synchronized void setVoteresult(int index) throws RemoteException {
        voteresult[index] = voteresult[index] + 1;
    }

    public synchronized void addPlayerCounter() throws RemoteException{
        i++;
    }

    public synchronized int getPlayerCounter() throws RemoteException {
        return i;
    }

    public synchronized void decreasePlayerCounter() {
        i--;
    }

    public boolean getInitMap() {
        return initmap;
    }

    public synchronized void setInitmap() {
        if (initmap == false) {
            initmap = true;
        }
    }

    public void setI(int i) throws RemoteException{
        this.i = i;
    }
}
