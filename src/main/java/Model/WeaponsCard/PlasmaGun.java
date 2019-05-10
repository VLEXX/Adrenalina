/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;
import Model.Exceptions.PlayerAlreadyAdded;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import Model.Exceptions.PositionUnreachable;

/**
 * Weapon Plasmagun
 */
public class PlasmaGun extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public PlasmaGun(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.BLUE);
    }

    /**
     * Funzione attacco base
     * @param player giocatore
     * @param myPosition
     * @param positionToAttack
     * @param playerToAttack
     * @return plancia giocatore da attaccare
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard attack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
        check(myPosition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, player);
        return playerToAttack;
    }

    /**
     * Funzione slittamento di fase
     * @param player giocatore che attacca
     * @param myposition posizione giocatore che attacca
     * @param positionToGo posizione dove il giocatore vuole andare
     * @return mypPosition posizione dove il giocatore vuole spostarsi
     * @throws PositionUnreachable
     * @throws PlayerNotFound
     * @throws PlayerAlreadyAdded
     * @author Giulia Rivara
     */
    public Position phaseSlip(Player player, Position myposition, Position positionToGo) throws PositionUnreachable, PlayerNotFound, PlayerAlreadyAdded {
        if(checkPosition(myposition, positionToGo)) {
            myposition.getCurrentcell().removeInCellPlayer(player);
            myposition.setCurrentcell(positionToGo.getCurrentcell());
            myposition.getCurrentcell().addInCellPlayer(player);
        }
        else throw new PositionUnreachable("Position with room ID " + positionToGo.getCurrentroom().getRoomId() + " and cell ID "+ positionToGo.getCurrentcell().getCellId() + "unreachable");
        return myposition;
    }

    /**
     * Funzione colpo sovraccarico
     * @param player giocatore che usa la carta
     * @param myPosition posizione giocatore che usa la carta
     * @param positionToAttack posizione del giocatore da attaccare
     * @param playerToAttack giocatore da attaccare
     * @return playerToAttack plancia giocatore attaccato
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard overloadedShot(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
        check(myPosition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, player);
        return playerToAttack;
    }

    /**
     * Controlla che la posizione sia corretta e che il giocatore in quella posizione sia presente
     * @param myPosition posizione giocatore che gioca la carta
     * @param playerToAttack giocatore da attaccare
     * @param positionToAttack posizione giocatore da attaccare
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

    /**
     *Controllo che la posizione dove voglio spostarmi sia raggiungibile
     * @param myPosition posizione del giocatore che attacca
     * @param positionToGo posizione in cui il giocatore che attacca vuole spostarsi
     * @return true se la posizione va bene
     * @author Giulia Rivara
     */
    private boolean checkPosition(Position myPosition, Position positionToGo){
        if(myPosition.getCurrentcell().getUpCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getUpCell(), positionToGo.getCurrentcell()))
                return true;
        }
        if(myPosition.getCurrentcell().getDownCell() != null){
            if(checkAround(myPosition.getCurrentcell().getDownCell(), positionToGo.getCurrentcell())){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getLeftCell() != null){
            if(checkAround(myPosition.getCurrentcell().getLeftCell(), positionToGo.getCurrentcell())){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getRightCell() != null){
            if(checkAround(myPosition.getCurrentcell().getRightCell(), positionToGo.getCurrentcell())){
                return true;
            }
        }
        return false;
    }

    /**
     * Controlla che la posizione dove il giocatore vuole spostarsi sia accessibile
     * @param current cella corrente
     * @param go cella dove voglio andare
     * @return true se posso spostarmi
     * @author Giulia Rivara
     */
    private boolean checkAround(Cell current, Cell go){
        if(current.getCellId() == go.getCellId()){
            return true;
        }
        if(current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if(current.getDownCell() != null){
            if(current.getDownCell().getCellId() == go.getCellId()){
                return true;
            }
        }
        if(current.getLeftCell() != null){
            if(current.getLeftCell().getCellId() == go.getCellId()){
                return true;
            }
        }
        if(current.getRightCell() != null){
            if(current.getRightCell().getCellId() == go.getCellId()){
                return true;
            }
        }
        return false;
    }
}
