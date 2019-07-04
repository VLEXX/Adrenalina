/**
 * @author Federico Scatà
 */
package model.gamedata;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Classe che memorizza la modalità corrente selezionata del gioco
public class StateSelectedMode extends UnicastRemoteObject implements Serializable, StateSelectedModeInterface {
    private Mode selectedmode;  //modalità selezionata del gioco

    //Costruttore che inizializza la modalità a "null"
    public StateSelectedMode() throws RemoteException {
        selectedmode = null;
    }

    //Ritorna la mappa selezionata
    public Mode getSelectedmode() {
        return selectedmode;
    }

    //Setta la modalità selezionata
    public void setSelectedmode(Mode selectedmode) throws RemoteException {
        this.selectedmode = selectedmode;
    }
}
