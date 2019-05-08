/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class GrenadeLauncher extends WeaponsDad{

    //Costruttore
    public GrenadeLauncher() {
        super();
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.RED, 1);
    }
}
