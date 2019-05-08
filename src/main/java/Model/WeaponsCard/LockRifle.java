/**
 *@author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;

public class LockRifle {

    private boolean firstUse;
    private final int priceToPay = 2;
    private final Munitions munitionsToPay = Munitions.BLUE;
    private final int priceToPayHooking = 1;
    private final Munitions munitionsToPayHooking = Munitions.RED;

    //Costruttore
    public LockRifle() {
        firstUse = true;
    }

    //Funzione effetto base
    public PlayerBoard attack(Position myPosition, Player activePlayer, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound {
        check(activePlayer,myPosition,positionToAttack,playerToAttack);
        if(firstUse == true)
            firstUse = false;
        playerToAttack.getDamageBox().increaseDamage(2, activePlayer);
        playerToAttack.getMarksBox().setMyMarksMap(activePlayer, 1);
        return playerToAttack;
    }

    //Funzione secondo aggancio
    public PlayerBoard hooking(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
        check(player,myPosition,positionToAttack,playerToAttack);
        playerToAttack.getMarksBox().setMyMarksMap(player, 1);
        return playerToAttack;
    }

    //Controlla che la pposizione sia corretta e che il giocatore in quella posizione sia presente
    private void check(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
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

    //Funzione che controlla che la carta sia stata usata una volta
    public boolean isFirstUse() {
        return firstUse;
    }

    //Funzione che ritorna le munizioni da pagare
    public Munitions getMunitionsToPay() {
        return munitionsToPay;
    }

    //Funzione che ritorna il costo dell'arma
    public int getPriceToPay() {
        return priceToPay;
    }

    //Funzione che ritorna il costo da pagare per il secondo aggancio
    public int getPriceToPayHooking() {
        return priceToPayHooking;
    }

    //Funzione che ritorna le munizioni da pagare per il secondo aggancio
    public Munitions getMunitionsToPayHooking() {
        return munitionsToPayHooking;
    }
}



