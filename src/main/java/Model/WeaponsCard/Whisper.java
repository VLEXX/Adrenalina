/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Whisper extends Weapon {

    //Costruttore
    public Whisper(){
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
    }
}
