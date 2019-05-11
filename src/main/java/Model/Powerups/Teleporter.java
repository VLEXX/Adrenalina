package Model.Powerups;

import Model.Munitions;
import Model.PowerUp;
import Model.PowerUpId;

public class Teleporter extends PowerUp {

    public Teleporter(PowerUpId Id, Munitions m) {
        super(PowerUpId.TELEPORTER, m);
    }
}
