/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Furnace extends WeaponsDad{

    //Costruttore
    private Furnace() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
    }
}
