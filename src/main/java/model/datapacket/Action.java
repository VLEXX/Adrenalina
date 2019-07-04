package model.datapacket;

import java.io.Serializable;

/**
 * an enum containing all possible action that a player can choose
 */
public enum Action implements Serializable {
    SHOOT,
    MOVE,
    PICK_UP,
    ENDTURN
}
