/**
 * @author Giulia Rivara
 */
package Model;

import java.io.Serializable;
import java.util.HashMap;

//Classe che gestisce i marchi giocatore tramite una mappa che tiene conto del numero dei marchi corrispondente al colore
public class MarksBox implements Serializable {
    private HashMap<Player, Integer> myMarksMap;

    //Costruttore per il numero dei marchi
    public MarksBox() {
        myMarksMap = new HashMap<>();
    }

    //Ritorna la mappa marchi
    public HashMap<Player, Integer> getMyMarksMap() {
        return myMarksMap;
    }

    //Setta la mappa marchi
    public void setMyMarksMap(Player player, int damage) {
        int i;
        if (myMarksMap.get(player) != null) {
            i = myMarksMap.get(player).intValue();
            i = i + damage;
            myMarksMap.replace(player, i);
        } else {
            myMarksMap.put(player, damage);
        }
    }
}


