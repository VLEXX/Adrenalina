package model.powerups;

import model.munitions.Munitions;
import model.powerup.PowerUp;
import model.powerup.PowerUpId;

public class Newton extends PowerUp {

    public Newton(Munitions m) {
        super(PowerUpId.NEWTON, m);
    }
}
