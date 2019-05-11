package Model.Powerups;

import Model.Munitions;
import Model.PowerUp;
import Model.PowerUpId;

public class TagbackGrenade extends PowerUp {
    public TagbackGrenade(Munitions m) {
        super(PowerUpId.TAGBACK_GRENADE, m);
    }
}
