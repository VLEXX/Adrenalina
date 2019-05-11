/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Exceptions.PositionUnreachable;
import Model.Munitions;
import Model.Player;
import Model.PlayerBoard;
import Model.Position;

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
    }

    /**
     * Function first attack
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position of the player to attack
     * @return playerboard of the player to attack
     * @throws PositionUnreachable
     * @author Giulia Rivara
     */
    public PlayerBoard firstAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack) throws PositionUnreachable{
        for(int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            if (myPosition.getCurrentcell().getInCellPlayer().get(i) == positionToAttack.getCurrentcell().getInCellPlayer().get(i)) {
                if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                    playerToAttack.getDamageBox().increaseDamage(1, player);
                }
            }
            else throw new PositionUnreachable("The player isn't in your cell");
        }
        return playerToAttack;
    }

    /**
     * Function for the reaper attack
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position of the player to attack
     * @return playerboard of the player to attack
     * @throws PositionUnreachable
     * @author Giulia Rivara
     */
    public PlayerBoard secondAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack) throws PositionUnreachable{
        for(int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                if (myPosition.getCurrentcell().getInCellPlayer() == positionToAttack.getCurrentcell().getInCellPlayer()) {
                    playerToAttack.getDamageBox().increaseDamage(2, player);
                }
            }
            else throw new PositionUnreachable("The player isn't in your cell");
        }
        return playerToAttack;
    }
}
