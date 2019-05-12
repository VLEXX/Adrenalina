/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import Model.WeaponsMessage;

/**
 * Weapon Sledgehammer
 */
public class SledgeHammer extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public SledgeHammer() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
    }
}
