/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

/**
 * Weapon Electroscythe
 */
public class Electroscythe extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public Electroscythe() {
        super();
        super.getFirstPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
    }

    /**
     * Function first attack
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position of the player to attack
     * @return OK or Position_UNREACHABLE
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack){
        for(int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            if (myPosition.getCurrentcell().getInCellPlayer().get(i) == positionToAttack.getCurrentcell().getInCellPlayer().get(i)) {
                if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                    playerToAttack.getDamageBox().increaseDamage(1, player);
                }
            }
            else return MessageEnum.POSITION_UNREACHABLE;
        }
        return MessageEnum.OK;
    }

    /**
     * Function for the reaper attack
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position of the player to attack
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack){
        for(int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                if (myPosition.getCurrentcell().getInCellPlayer() == positionToAttack.getCurrentcell().getInCellPlayer()) {
                    playerToAttack.getDamageBox().increaseDamage(2, player);
                }
            }
            else return MessageEnum.POSITION_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
