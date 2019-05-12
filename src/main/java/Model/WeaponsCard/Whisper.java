/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

/**
 * Weapon Whisper
 */
public class Whisper extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public Whisper() {
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(false);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
    }

    /**
     * Function first attack
     * @param player player who shot
     * @param myPosition position of the player who shot
     * @param playerToShot player to shot
     * @param positionToShot position of the player to shot
     * @return Ok or PLAYER_UNREACHABLE
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player player, Position myPosition, PlayerBoard playerToShot, Position positionToShot){
        if(checkPosition(myPosition, positionToShot)){
            playerToShot.getDamageBox().increaseDamage(3, player);
            playerToShot.getMarksBox().setMyMarksMap(player, 1);
        }
        else return MessageEnum.PLAYER_UNREACHABLE;
        return MessageEnum.OK;
    }

    /**
     * Function that check the correct position to shot
     * @param myPosition position of the player who shot
     * @param positionToShot position to shot
     * @return true if correct
     * @author Giulia Rivara
     */
    private boolean checkPosition(Position myPosition, Position positionToShot){
        if(myPosition.getCurrentcell().getUpCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getUpCell(), positionToShot.getCurrentcell()))
                return true;
        }
        if(myPosition.getCurrentcell().getDownCell() != null){
            if(checkAround(myPosition.getCurrentcell().getDownCell(), positionToShot.getCurrentcell())){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getLeftCell() != null){
            if(checkAround(myPosition.getCurrentcell().getLeftCell(), positionToShot.getCurrentcell())){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getRightCell() != null){
            if(checkAround(myPosition.getCurrentcell().getRightCell(), positionToShot.getCurrentcell())){
                return true;
            }
        }
        return false;
    }

    /**
     * Function that check the correct position to shot
     * @param current current cell of the player
     * @param shot cell to shot
     * @return true if correct
     * @author Giulia Rivara
     */
    private boolean checkAround(Cell current, Cell shot){
        if(current.getCellId() == shot.getCellId()){
            return true;
        }
        if(current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == shot.getCellId()) {
                return true;
            }
        }
        if(current.getDownCell() != null){
            if(current.getDownCell().getCellId() == shot.getCellId()){
                return true;
            }
        }
        if(current.getLeftCell() != null){
            if(current.getLeftCell().getCellId() == shot.getCellId()){
                return true;
            }
        }
        if(current.getRightCell() != null){
            if(current.getRightCell().getCellId() == shot.getCellId()){
                return true;
            }
        }
        return false;
    }
}
