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
    WEAPON_NOT_FOUND,
    POSITION_NOT_FOUND,
    NOT_SHOT_AGAIN,
    PLAYER_UNREACHABLE,
    CANNOT_USE_THIS_EFFECT,
    POSITION_UNREACHABLE,
    PLAYER_NOT_FOUND,
    PLAYERS_NOT_VALID
}
