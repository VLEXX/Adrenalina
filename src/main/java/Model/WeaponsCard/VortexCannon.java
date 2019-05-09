/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class VortexCannon extends Weapon {

    //Costruttore
    public VortexCannon(){
        super();
        super.setFirstPrice(Munitions.RED, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.RED, 1);
    }
}
