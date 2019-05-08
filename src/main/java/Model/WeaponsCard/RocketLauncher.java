/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class RocketLauncher extends WeaponsDad{

    //Costruttore
    private RocketLauncher(){
        super();
        super.setFirstPrice(Munitions.RED, 2);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.YELLOW, 1);
    }
}
