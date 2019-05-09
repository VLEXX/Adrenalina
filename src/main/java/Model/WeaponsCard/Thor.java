/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Thor extends Weapon {

    //Costruttore
    public Thor(){
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED,1);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
    }
}
