package model.powerups;

import model.munitions.Munitions;

public class Teleporter extends PowerUp {

    public Teleporter(Munitions m) {
        super(PowerUpId.TELEPORTER, m);
    }
}
