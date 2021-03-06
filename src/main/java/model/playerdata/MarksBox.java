/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.map.Map;

import java.io.*;
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
     */
    public HashMap<Player, Integer> getMyMarksMap() {
        return myMarksMap;
    }

    /**
     * set marks map
     * @param player
     * @param marx
     */
    public void setMyMarksMap(Player player, int marx) {
        int i;
        if (myMarksMap.get(player) != null) {
            i = myMarksMap.get(player);
            i = i + marx;
            if(i <= 3) {
                myMarksMap.replace(player, i);
            } else myMarksMap.replace(player, 3);
        } else {
            myMarksMap.put(player, marx);
        }
    }

    public HashMap<Player, Integer> deepCloneMarks() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(this.myMarksMap);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return (HashMap<Player, Integer>) objectInputStream.readObject();
    }
}


