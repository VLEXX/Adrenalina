/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import Model.WeaponsMessage;

/**
 * Weapon Shockwawe
 */
public class ShockWave extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public ShockWave() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_THREE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_ONEDISTANCE, 1);
    }
}
