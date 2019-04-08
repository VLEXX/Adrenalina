//Author: Giulia Rivara
package Model;

import java.util.HashMap;

//Classe che gestisce le munizioni del giocatore
public class MunitionsBox {
    private HashMap<Player, Integer> myMunitionsMap;

    //Costruttore delle munizioni
    public MunitionsBox(){
        myMunitionsMap = new HashMap<>();
    }

    //Ritorna le munizioni gioatore
    public HashMap<Player, Integer> getMyMunitionsMap() {
        return myMunitionsMap;
    }

    //Setta le munizioni giocatore
    public void setMyMunitionsMap(Player player, int damage) {
        int i;
        if (myMunitionsMap.get(player)!=null){
            i = myMunitionsMap.get(player).intValue();
            i = i + damage;
            myMunitionsMap.replace(player, i);
        } else {
            myMunitionsMap.put(player, damage);
        }
    }
}
