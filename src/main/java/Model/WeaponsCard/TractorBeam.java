/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class TractorBeam extends Weapon {

    //Costruttore
    public TractorBeam(){
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.YELLOW,1);
        super.setSecondPrice(Munitions.RED, 1);
    }
}
