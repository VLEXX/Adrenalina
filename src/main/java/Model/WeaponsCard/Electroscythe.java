/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Electroscythe extends WeaponsDad{

    //Costruttore
    public Electroscythe(){
        super();
        super.getFirstPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.RED, 1);
    }
}
