package model.powerups;

import model.munitions.Munitions;
import model.powerup.PowerUp;
import model.powerup.PowerUpId;

public class TagbackGrenade extends PowerUp {
    public TagbackGrenade(Munitions m) {
        super(PowerUpId.TAGBACK_GRENADE, m);
    }
}
