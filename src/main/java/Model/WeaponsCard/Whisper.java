/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * Weapon Whisper
 */
public class Whisper extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public Whisper() {
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(false);
        super.setThirdAttack(false);
    }
}
