package Model.Powerups;

import Model.Munitions;
import Model.PowerUp;
import Model.PowerUpId;

public class TargetingScope extends PowerUp {

    public TargetingScope(PowerUpId Id, Munitions m) {
        super(PowerUpId.TARGETING_SCOPE, m);
    }
}
