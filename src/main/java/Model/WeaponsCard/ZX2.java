/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Exceptions.DontUseEffect;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import Model.Munitions;
import Model.Player;
import Model.PlayerBoard;
import Model.Position;

/**
 * Weapon ZX2
 */
public class ZX2 extends Weapon {

    public Player player1;
    public Player player2;
    public Position position1;
    public Position position2;

    /**
     * Constructor that set the cost of this weapon
     */
    public ZX2() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.YELLOW);
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
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard firstAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack) throws PositionNotFound, PlayerNotFound {
        check(myPosition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, player);
        playerToAttack.getMarksBox().setMyMarksMap(player, 2);
        return playerToAttack;
    }

    /**
     * Function scanner mode
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position of the player to attack
     * @return playerboard of the player to attack
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @throws DontUseEffect
     * @author Giulia Rivara
     */
    public PlayerBoard secondAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack) throws PositionNotFound, PlayerNotFound, DontUseEffect {
        int cont = 0;
        do {
            check(myPosition, positionToAttack, playerToAttack);
            playerToAttack.getMarksBox().setMyMarksMap(player, 1);
            player1 = playerToAttack.getPlayer();
            cont++;
            if (playerToAttack.getPlayer() != player1) {
                check(myPosition, positionToAttack, playerToAttack);
                playerToAttack.getMarksBox().setMyMarksMap(player, 1);
                player2 = playerToAttack.getPlayer();
                cont++;
            }
            if (playerToAttack.getPlayer() != player1 && playerToAttack.getPlayer() != player2) {
                check(myPosition, positionToAttack, playerToAttack);
                playerToAttack.getMarksBox().setMyMarksMap(player, 1);
                cont++;
            }
        } while (cont < 4);
        if(cont > 3)
            throw new DontUseEffect("You can't shot again");
        return playerToAttack;
    }

    /**
     * Function that check for correct position and correct player
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack player to attack
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    private void check(Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
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
