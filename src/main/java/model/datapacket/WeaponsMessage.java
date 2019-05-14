package model.datapacket;

import java.io.Serializable;

public enum WeaponsMessage implements Serializable {
    MAX_ONE_PLAYER,
    MAX_TWO_PLAYER,
    MAX_THREE_PLAYER,
    ALL_PLAYER_INCELL,
    ALL_PLAYER_INTWOCELL,
    MYPLAYER,
    MAX_ONE_PLAYER_FORCELL,
    ALL_PLAYER_ONEDISTANCE
}
