/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ShockWave extends WeaponsDad{

    //Costruttore
    private ShockWave(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
    }
}
