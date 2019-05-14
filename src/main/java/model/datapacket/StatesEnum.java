/**
 * @author Federico Scatà
 */
package model.datapacket;

import java.io.Serializable;

public enum StatesEnum implements Serializable {
    START,
    END,
    SHOOT,
    MOVE,
    PICK_UP,
    WAIT,
    MID
}
