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
    public Thor(){
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED,1);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.BLUE);
    }

    //Funzione effetto base
    public PlayerBoard attack(Position myposition, Player player, Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound {
        check(myposition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, player);
        position1 = positionToAttack;
        myPlayer = player;
        return playerToAttack;
    }

    //Funzione reazione a catena
    public PlayerBoard reactToChain(Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound{
        check(position1, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, myPlayer);
        position2 = positionToAttack;
        return playerToAttack;
    }

    //Funzione alta tensione
    public PlayerBoard highVoltage(Position positionToAttack, PlayerBoard playerToAttack) throws PlayerNotFound, PositionNotFound{
        check(position2, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, myPlayer);
        return playerToAttack;
    }

    //Controlla che la pposizione sia corretta e che il giocatore in quella posizione sia presente
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
