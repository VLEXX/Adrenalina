/**
 * @author Federico Scatà
 */
package model.datapacket;

import java.io.Serializable;

public enum StatesEnum implements Serializable  {
    ACTION,
    END,
    SHOOT,
    SHOOT_SECOND,
    SHOOT_THIRD,
    MOVE,
    PICK_UP,
    WAIT,
    POWERUP
}
