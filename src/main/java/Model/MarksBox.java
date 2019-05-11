/**
 * @author Giulia Rivara
 */
package Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that manages the player marks through a map that takes into account the number of brands corresponding to the color
 */
public class MarksBox implements Serializable {
    private HashMap<Player, Integer> myMarksMap;

    /**
     * Constructor
     */
    public MarksBox() {
        myMarksMap = new HashMap<>();
    }

    /**
     * @return marks map
     * @author Giulia Rivara
     */
    public HashMap<Player, Integer> getMyMarksMap() {
        return myMarksMap;
    }

    /**
     * set marks map
     * @param player
     * @param damage
     * @author Giulia Rivara
     */
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


