package model.powerups;

import model.munitions.Munitions;
import model.powerup.PowerUp;
import model.powerup.PowerUpId;

public class TargetingScope extends PowerUp {

    public TargetingScope(Munitions m) {
        super(PowerUpId.TARGETING_SCOPE, m);
    }
}
