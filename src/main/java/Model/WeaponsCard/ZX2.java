/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

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
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack){
        check(myPosition, positionToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, player);
        playerToAttack.getMarksBox().setMyMarksMap(player, 2);
        return MessageEnum.OK;
    }

    /**
     * Function scanner mode
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position of the player to attack
     * @return Ok or NOT_SHOT_AGAIN
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player player, Position myPosition, PlayerBoard playerToAttack, Position positionToAttack){
        int cont = 0;
        do {
            check(myPosition, positionToAttack);
            playerToAttack.getMarksBox().setMyMarksMap(player, 1);
            player1 = playerToAttack.getPlayer();
            cont++;
            if (playerToAttack.getPlayer() != player1) {
                check(myPosition, positionToAttack);
                playerToAttack.getMarksBox().setMyMarksMap(player, 1);
                player2 = playerToAttack.getPlayer();
                cont++;
            }
            if (playerToAttack.getPlayer() != player1 && playerToAttack.getPlayer() != player2) {
                check(myPosition, positionToAttack);
                playerToAttack.getMarksBox().setMyMarksMap(player, 1);
                cont++;
            }
        } while (cont < 4);
        if(cont > 3) {
            return MessageEnum.NOT_SHOT_AGAIN;
        }
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position and correct player
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    private MessageEnum check(Position myPosition, Position positionToAttack){
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            return MessageEnum.POSITION_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
