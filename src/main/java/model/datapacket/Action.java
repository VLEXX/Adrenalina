package model.datapacket;

import java.io.Serializable;

public enum Action implements Serializable {
    SHOOT,
    MOVE,
    PICK_UP,
    ENDTURN
}
