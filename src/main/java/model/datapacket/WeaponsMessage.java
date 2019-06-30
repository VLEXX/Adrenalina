package model.datapacket;

import java.io.Serializable;

public enum WeaponsMessage implements Serializable {
    MAX_ONE_PLAYER,
    MAX_ONE_PLAYER_MAX_TWO_CELL,
    MAX_TWO_PLAYER,
    MAX_THREE_PLAYER,
    MAX_ONE_CELL,
    MAX_ONE_CELL_MAX_ONE_PLAYER,
    MAX_TWO_CELL,
    MAX_TWO_CELL_MAX_TWO_PLAYER,
    MAX_ONE_ROOM,
    NOTHING
}
