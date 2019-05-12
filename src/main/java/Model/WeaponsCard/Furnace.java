/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import Model.WeaponsMessage;

/**
 * Weapon Furnace
 */
public class Furnace extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public Furnace() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
    }
}
