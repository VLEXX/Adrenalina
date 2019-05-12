/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.MessageEnum;
import Model.Munitions;
import Model.PlayerBoard;
import Model.WeaponsMessage;

/**
 * Weapon Tractorbeam
 */
public class TractorBeam extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public TractorBeam(){
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.YELLOW,1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
    }

    public MessageEnum fisrtAttack(){
        return MessageEnum.OK;
    }
}
