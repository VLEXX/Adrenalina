/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Cyberblade extends WeaponsDad{

    //Costruttore
    public Cyberblade(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
    }
}
