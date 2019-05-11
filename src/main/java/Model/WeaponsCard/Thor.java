/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import Model.Munitions;
import Model.Player;
import Model.PlayerBoard;
import Model.Position;

/**
 * Weapon Thor
 */
public class Thor extends Weapon {

    private Position position1;
    private Position position2;
    private Player myPlayer;

    /**
     * Constructor that set the cost of this weapon
     */
    public Thor() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
    }

    /**
     * Function first attack
     *
     * @param myposition       position of the player who attack
     * @param player           player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return playerboard of the player to attack
     * @throws PlayerNotFound
     * @throws PositionNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard firstAttack(Position myposition, Player player, Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound {
        check(myposition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, player);
        position1 = positionToAttack;
        myPlayer = player;
        return playerToAttack;
    }

    /**
     * Function react to chain
     *
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attackk
     * @return playerboard of the player to attack
     * @throws PlayerNotFound
     * @throws PositionNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard secondAttack(Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound {
        check(position1, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, myPlayer);
        position2 = positionToAttack;
        return playerToAttack;
    }

    /**
     * Function high voltage
     *
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return playerboard of the player to attack
     * @throws PlayerNotFound
     * @throws PositionNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard thirdAttack(Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound {
        check(position2, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, myPlayer);
        return playerToAttack;
    }

    /**
     * Function that check for correct position and correct player
     *
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    private void check(Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            throw new PositionNotFound("Position not found for " + positionToAttack.getCurrentcell().getCellId());
        find = false;
        for (int i = 0; i < positionToAttack.getCurrentcell().getInCellPlayer().size(); i++) {
            if (positionToAttack.getCurrentcell().getInCellPlayer().get(i) == playerToAttack.getPlayer()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            throw new PlayerNotFound("In the selected cell player " + playerToAttack.getPlayer().toString() + " not found");
        }
    }
}
