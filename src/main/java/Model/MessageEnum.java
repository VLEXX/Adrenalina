package Model;

import java.io.Serializable;

public enum MessageEnum implements Serializable {
    OK,
    UNREACHABLE_CELL,
    AMMO_ERROR,
    WEAPON_ERROR,
    NOT_YOUR_TURN,
    ACTION_ERROR,
    EMPTY_WEAPON,
    WEAPON_NOT_FOUND
}
