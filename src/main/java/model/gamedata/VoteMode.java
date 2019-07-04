/**
 * @author Federico Scatà
 */
package model.gamedata;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Classe che gestisce la votazione per la scelta della modalità in cui giocare
public class VoteMode extends UnicastRemoteObject implements Serializable,VoteModeInterface {
    private int[] voteresultmode;   //Array che memorizza i voti ottenuti da ciascuna modalità (VoteResult[0] => BASE, VoteResult[1] => Dominazione, ...)
    private Mode finalresultmode;    //Memorizza la modalità finale votata (da 1 a 3)

    //Costruttore che inizialzza l'array a 0 e la modalità finale votata a -1 (le modalità vanno da 1 a 3)
    public VoteMode() throws RemoteException {
        voteresultmode = new int[]{0, 0};
        finalresultmode = null;
    }

    //Ritorna la modalità finale votata
    public Mode getFinalResult() throws RemoteException {
        return finalresultmode;
    }

    //Setta la modalità finale votata
    public void setFinalResult() throws RemoteException{
        if(voteresultmode[0]>=voteresultmode[1]){
            finalresultmode = Mode.BASE;
        }
        else{
            finalresultmode = Mode.DOMINATION;
        }
    }

    //Ritorna l'array con i voti
    public int[] getVoteResult() {
        return voteresultmode;
    }

    //Setta il voto per la modalità, a seconda dell'indice passato (che corrisponde alla modalità votata, 0,1,2)
    public void setVoteResult(int index) throws RemoteException {
        voteresultmode[index] = voteresultmode[index] + 1;
    }
}
