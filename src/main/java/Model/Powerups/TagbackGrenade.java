package Model.Powerups;

import Model.Munitions;
import Model.PowerUp;
import Model.PowerUpId;

public class TagbackGrenade extends PowerUp {
    public TagbackGrenade(PowerUpId Id, Munitions m) {
        super(PowerUpId.TAGBACK_GRENADE, m);
    }
}
