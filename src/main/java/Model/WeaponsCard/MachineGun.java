/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Exceptions.PlayerAlreadyAdded;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import Model.Munitions;
import Model.Player;
import Model.PlayerBoard;
import Model.Position;

import java.util.ArrayList;

public class MachineGun extends WeaponsDad {

    //Costruttore
    private MachineGun() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
    }

    //Funzione effetto base
    public PlayerBoard attack(Player player, Position myPosition, Position positionToAttack1, PlayerBoard playerToAttack1, Position positionToAttack2, PlayerBoard playerToAttack2, Position positionToAttack3, PlayerBoard playerToAttack3) throws PlayerNotFound, PositionNotFound {
        check(player, myPosition, positionToAttack1, playerToAttack1, positionToAttack2, playerToAttack2, positionToAttack3, playerToAttack3);
        playerToAttack1.getDamageBox().increaseDamage(1, player);
        playerToAttack2.getDamageBox().increaseDamage(1, player);
        return playerToAttack1;
    }

    //Funzione colpo focalizzato
    public ArrayList<PlayerBoard> focusedShot(Player player, Position myPosition, Position positionToAttack1, PlayerBoard playerToAttack1, Position positionToAttack2, PlayerBoard playerToAttack2, Position positionToAttack3, PlayerBoard playerToAttack3) throws PlayerNotFound, PositionNotFound{
        ArrayList<PlayerBoard> playerToAttack = new ArrayList<>();
        playerToAttack.add(playerToAttack1);
        playerToAttack.add(playerToAttack2);
        check(player, myPosition, positionToAttack1, playerToAttack1, positionToAttack2, playerToAttack2, positionToAttack3, playerToAttack3);
        playerToAttack1.getDamageBox().increaseDamage(1, player);
        playerToAttack2.getDamageBox().increaseDamage(1, player);
        return playerToAttack;
    }

    //Funzione tripode di supporto
    public ArrayList<PlayerBoard> tripod(Player player, Position myPosition, Position positionToAttack1, PlayerBoard playerToAttack1, Position positionToAttack2, PlayerBoard playerToAttack2, Position positionToAttack3, PlayerBoard playerToAttack3) throws PlayerNotFound, PositionNotFound{
        ArrayList<PlayerBoard> playerToAttack = new ArrayList<>();
        playerToAttack.add(playerToAttack1);
        playerToAttack.add(playerToAttack2);
        playerToAttack.add(playerToAttack3);
        check(player, myPosition, positionToAttack1, playerToAttack1, positionToAttack2, playerToAttack2, positionToAttack3, playerToAttack3);
        playerToAttack1.getDamageBox().increaseDamage(1, player);
        playerToAttack2.getDamageBox().increaseDamage(1, player);
        playerToAttack3.getDamageBox().increaseDamage(1, player);
        return playerToAttack;
    }

    //Controlla che la pposizione sia corretta e che il giocatore in quella posizione sia presente
    private void check(Player player, Position myPosition, Position positionToAttack1, PlayerBoard playerToAttack1, Position positionToAttack2, PlayerBoard playerToAttack2, Position positionToAttack3, PlayerBoard playerToAttack3) throws PositionNotFound, PlayerNotFound {
        boolean find = false;
        //Controllo sul primo bersaglio
        if (playerToAttack1.getPlayer() != null) {
            for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
                if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack1.getCurrentcell().getCellId()) {
                    find = true;
                    break;
                }
            }
            if (find = false) {
                throw new PositionNotFound("Position not found for " + positionToAttack1.getCurrentcell().getCellId());
            }
        }
        find = false;
        for (int i = 0; i < positionToAttack1.getCurrentcell().getInCellPlayer().size(); i++) {
            if (positionToAttack1.getCurrentcell().getInCellPlayer().get(i) == playerToAttack1.getPlayer()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            throw new PlayerNotFound("In the selected cell player " + playerToAttack1.getPlayer().toString() + " not found");
        }
        //Controllo sul secondo bersaglio
        if (playerToAttack2.getPlayer() != null) {
            for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
                if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack2.getCurrentcell().getCellId()) {
                    find = true;
                    break;
                }
            }
            if (find = false) {
                throw new PositionNotFound("Position not found for " + positionToAttack2.getCurrentcell().getCellId());
            }
        }
        find = false;
        for (int i = 0; i < positionToAttack2.getCurrentcell().getInCellPlayer().size(); i++) {
            if (positionToAttack2.getCurrentcell().getInCellPlayer().get(i) == playerToAttack2.getPlayer()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            throw new PlayerNotFound("In the selected cell player " + playerToAttack2.getPlayer().toString() + " not found");
        }
        //Controllo sul terzo bersaglio
        if (playerToAttack3.getPlayer() != null) {
            for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
                if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack3.getCurrentcell().getCellId()) {
                    find = true;
                    break;
                }
            }
            if (find = false) {
                throw new PositionNotFound("Position not found for " + positionToAttack3.getCurrentcell().getCellId());
            }
        }
        find = false;
        for (int i = 0; i < positionToAttack3.getCurrentcell().getInCellPlayer().size(); i++) {
            if (positionToAttack3.getCurrentcell().getInCellPlayer().get(i) == playerToAttack3.getPlayer()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            throw new PlayerNotFound("In the selected cell player " + playerToAttack3.getPlayer().toString() + " not found");
        }
    }
}