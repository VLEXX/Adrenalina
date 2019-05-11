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

import java.util.ArrayList;

/**
 * Weapon Machinegun
 */
public class MachineGun extends Weapon {

    boolean player1Attacked;
    private Player player1;
    private Player player2;

    /**
     * Constructor that set the cost of this weapon
     */
    public MachineGun() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        player1Attacked = false;
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
    }

    /**
     * Function first attack
     *
     * @param player            player who attack
     * @param myPosition        position of the player who attack
     * @param positionToAttack1 position of the first player to attack
     * @param playerToAttack1   first player to attack
     * @param positionToAttack2 position of the second player to attack
     * @param playerToAttack2   second player to attack
     * @return playerboard of the player
     * @throws PlayerNotFound
     * @throws PositionNotFound
     * @author Giulia Rivara
     */
    public ArrayList<PlayerBoard> firstAttack(Player player, Position myPosition, Position positionToAttack1, PlayerBoard playerToAttack1, Position positionToAttack2, PlayerBoard playerToAttack2) throws PlayerNotFound, PositionNotFound {
        ArrayList<PlayerBoard> playerToAttacks = new ArrayList();
        player1 = playerToAttack1.getPlayer();
        player2 = playerToAttack2.getPlayer();
        if (positionToAttack1 != null && playerToAttack1 != null) {
            check(myPosition, positionToAttack1, playerToAttack1);
            playerToAttack1.getDamageBox().increaseDamage(1, player);
            playerToAttacks.add(playerToAttack1);
        }
        if (positionToAttack2 != null && playerToAttack2 != null) {
            check(myPosition, positionToAttack2, playerToAttack2);
            playerToAttack2.getDamageBox().increaseDamage(1, player);
            playerToAttacks.add(playerToAttack2);
        }
        return playerToAttacks;
    }

    /**
     * Function focused shot
     *
     * @param player           player who attack
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return playerboard  of the player
     * @throws PlayerNotFound
     * @throws PositionNotFound
     * @throws DontUseEffect
     * @author Giulia Rivara
     */
    public PlayerBoard secondAttack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound, DontUseEffect {
        if (player1 == null || player2 == null) {
            throw new DontUseEffect("You can't use this effect because there is only one player selected");
        }
        if (playerToAttack.getPlayer() != player1 && playerToAttack.getPlayer() != player2) {
            throw new DontUseEffect("You can't use this effect because you have selected a wrong player");
        }
        check(myPosition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, player);
        if (playerToAttack.getPlayer() == player1)
            player1Attacked = true;
        else player1Attacked = false;
        return playerToAttack;
    }

    /**
     * Function support tripods
     *
     * @param player            player who attack
     * @param myPosition        position of the player who attack
     * @param positionToAttack  position of the second player to attack
     * @param playerToAttack    first player to firstAttack
     * @param positionToAttack2 position of the second player to attack
     * @param playerToAttack2   second player to attack
     * @return playerboard of the player
     * @throws PlayerNotFound
     * @throws PositionNotFound
     * @throws DontUseEffect
     * @author Giulia Rivara
     */
    public ArrayList<PlayerBoard> thirdAttack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack, Position positionToAttack2, PlayerBoard playerToAttack2) throws PlayerNotFound, PositionNotFound, DontUseEffect {
        ArrayList<PlayerBoard> playerToAttacks = new ArrayList<>();
        if (player1 == null || player2 == null) {
            if (playerToAttack.getPlayer() == player1 || playerToAttack.getPlayer() == player2) {
                check(myPosition, positionToAttack, playerToAttack);
                playerToAttack.getDamageBox().increaseDamage(1, player);
                if (playerToAttack2 != null) {
                    check(myPosition, positionToAttack2, playerToAttack2);
                    playerToAttack2.getDamageBox().increaseDamage(1, player);
                }
            } else if (playerToAttack2.getPlayer() == player1 || playerToAttack2.getPlayer() == player2) {
                check(myPosition, positionToAttack2, playerToAttack2);
                playerToAttack2.getDamageBox().increaseDamage(1, player);
                if (playerToAttack != null) {
                    check(myPosition, positionToAttack, playerToAttack);
                    playerToAttack.getDamageBox().increaseDamage(1, player);
                }
            }
        } else if ((playerToAttack.getPlayer() == player1 && player1Attacked == false) || (playerToAttack.getPlayer() == player2 && player1Attacked == true)) {
            check(myPosition, positionToAttack, playerToAttack);
            playerToAttack.getDamageBox().increaseDamage(1, player);
            if (playerToAttack2 != null && playerToAttack2.getPlayer() != player1 && playerToAttack2.getPlayer() != player2) {
                check(myPosition, positionToAttack2, playerToAttack2);
                playerToAttack2.getDamageBox().increaseDamage(1, player);
            }
        } else if ((playerToAttack2.getPlayer() == player1 && player1Attacked == false) || (playerToAttack2.getPlayer() == player2 && player1Attacked == true)) {
            check(myPosition, positionToAttack2, playerToAttack2);
            playerToAttack2.getDamageBox().increaseDamage(1, player);
            if (playerToAttack != null && playerToAttack.getPlayer() != player1 && playerToAttack.getPlayer() != player2) {
                check(myPosition, positionToAttack, playerToAttack);
                playerToAttack.getDamageBox().increaseDamage(1, player);
            }
        } else throw new DontUseEffect("You can't use this effect");
        if (playerToAttack != null)
            playerToAttacks.add(playerToAttack);
        if (playerToAttack2 != null)
            playerToAttacks.add(playerToAttack2);
        return playerToAttacks;
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
        if (playerToAttack.getPlayer() != null) {
            for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
                if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                    find = true;
                    break;
                }
            }
            if (find = false) {
                throw new PositionNotFound("Position not found for " + positionToAttack.getCurrentcell().getCellId());
            }
        }
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