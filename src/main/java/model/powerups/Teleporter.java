package model.powerups;

import model.munitions.Munitions;
import model.powerup.PowerUp;
import model.powerup.PowerUpId;

public class Teleporter extends PowerUp {

    public Teleporter(Munitions m) {
        super(PowerUpId.TELEPORTER, m);
    }
}
