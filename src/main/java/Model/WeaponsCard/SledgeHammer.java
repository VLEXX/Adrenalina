/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class SledgeHammer extends Weapon {

    //Costruttore
    public SledgeHammer(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.RED, 1);
    }
}
