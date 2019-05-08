/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class PowerGlove extends WeaponsDad{

    //Costruttore
    private PowerGlove(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.BLUE, 1);
    }
}
