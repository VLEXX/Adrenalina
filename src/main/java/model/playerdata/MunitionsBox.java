/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.munitions.Munitions;

import java.io.Serializable;
import java.util.HashMap;

//Classe che gestisce le munizioni del giocatore
public class MunitionsBox implements Serializable, Cloneable {
    private HashMap<Munitions, Integer> myMunitionsMap;

    //Costruttore delle munizioni
    public MunitionsBox() {
        myMunitionsMap = new HashMap<>();
        myMunitionsMap.put(Munitions.YELLOW, 3);
        myMunitionsMap.put(Munitions.BLUE, 3);
        myMunitionsMap.put(Munitions.RED, 3);
    }

    //Ritorna le munizioni gioatore
    public HashMap<Munitions, Integer> getMyMunitionsMap() {
        return myMunitionsMap;
    }

    //Setta le munizioni giocatore
    public void increaseMyMunitionsBox(Munitions munitions, int cont) {
        int actual = myMunitionsMap.get(munitions);
        actual = cont + actual;
        myMunitionsMap.replace(munitions, actual);
    }

    //Elimina le munizioni giocatore
    public void decreaseMyMunitionsBox(Munitions munitions, int cont) {
        int actual = myMunitionsMap.get(munitions);
        actual = actual - cont;
        myMunitionsMap.replace(munitions, actual);
    }
}
