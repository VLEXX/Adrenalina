/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Hellion extends WeaponsDad{

    //Costruttore
    public Hellion(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.RED, 1);
    }
}
