/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.*;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Railgun
 */
public class RailGun extends Weapon implements Serializable {

    Map map;

    /**
     * Constructor that set the cost of this weapon
     */
    public RailGun() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 1);
        super.setName("railgun");
    }

    /**
     * Attack for the DOMINATION mode at the spawn point
     * @param myPlayer player who attack
     * @param spawnPoint
     * @param allPlay current state game
     * @return OK or ATTACK_NOT_PRESENT
     */
    public MessageEnum firstAttack(Player myPlayer, SpawnPoint spawnPoint, InitializeAllPlay allPlay){
        if(allPlay.getStateSelectedMode().getSelectedmode() == Mode.DOMINATION){
            Position positionSP = allPlay.getCurrentPlayerState().get(spawnPoint).getPlayerposition();
            Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
            if(spawnPoint != null) {
                if (positionSP == myPosition) {
                    spawnPoint.getSPDamage().add(myPlayer);
                }
            }
        } else
            return MessageEnum.ATTACK_NOT_PRESENT;
        return MessageEnum.OK;
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param cardinalDirection null
     * @param allPlay current state game
     * @return POSITION_NOT_VALID or OK
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position cardinalDirection, InitializeAllPlay allPlay){
        int control = 0;
        map = allPlay.getStateSelectedMap().getSelectedmap();
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(checkCardinal(myPosition, positionToAttack)){
            if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
            if(control != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, myPlayer);
        } else
            return MessageEnum.POSITION_NOT_VALID;
        return MessageEnum.OK;
    }

    /**
     * Function drilling mode attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param cardinalDirection null
     * @param allPlay current state game
     * @return POSITION_NOT_VALID or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position cardinalDirection, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        Position positionToAttack2 = null;
        if(playerToAttack.get(1) != null)
            positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition();
        if(checkCardinal(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_VALID;
        if(checkCardinal(myPosition, positionToAttack2) == false)
            return MessageEnum.POSITION_NOT_VALID;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function check for the correct cardinal position
     * @param myPosition my position
     * @param positionToAttack position cardinal to attack
     * @return true if correct
     */
    private boolean checkCardinal(Position myPosition, Position positionToAttack){
        //controllo che il giocatore sia o nella mia cella o sotto
        if(positionToAttack == myPosition || positionToAttack.getCurrentcell().getCellId() == myPosition.getCurrentcell().getCellId()+4 || positionToAttack.getCurrentcell().getCellId() == myPosition.getCurrentcell().getCellId()+8)
            return true;
        //controllo che il giocatore sia sopra di me
        if(positionToAttack.getCurrentcell().getCellId() == myPosition.getCurrentcell().getCellId()-4 || positionToAttack.getCurrentcell().getCellId() == myPosition.getCurrentcell().getCellId()-8)
            return true;
        Cell position = myPosition.getCurrentcell();
        Cell temp = null;
        for(int i = 0; i < 3; i++) {
            //controllo che il giocatore sia a sinistra con la porta
            if (position.getLeftCell() != null) {
                if (position.getLeftCell() == positionToAttack.getCurrentcell())
                    return true;
                else position = position.getLeftCell();
            } else {
                //controllo sotto se c'è la porta a sx
                temp = findCell(position.getCellId() + 4); //temp = cella sotto
                if (temp != null && temp.getLeftCell() != null) {
                    position = findCell(temp.getLeftCell().getCellId() - 4); //prendo ID cella alla mia sx oltre porta/muro
                    if (position == positionToAttack.getCurrentcell())
                        return true;
                } else {
                    //controllo sopra se c'è la porta a sx
                    temp = findCell(position.getCellId() - 4); //temp = cella sopra
                    if (temp != null && temp.getLeftCell() != null) {
                        position = findCell(temp.getLeftCell().getCellId() + 4);
                        if (position == positionToAttack.getCurrentcell())
                            return true;
                    } else
                        break;
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            //controllo che il giocatore sia a destra con la porta
            if (position.getRightCell() != null) {
                if (position.getRightCell() == positionToAttack.getCurrentcell())
                    return true;
                else position = position.getRightCell();
            } else {
                //controllo sotto se c'è la porta a dx
                temp = findCell(position.getCellId() + 4); //temp = cella sotto
                if (temp != null && temp.getRightCell() != null) {
                    position = findCell(temp.getRightCell().getCellId() - 4); //prendo ID cella alla mia dx oltre porta/muro
                    if (position == positionToAttack.getCurrentcell())
                        return true;
                } else {
                    //controllo sopra se c'è la porta a dx
                    temp = findCell(position.getCellId() - 4); //temp = cella sopra
                    if (temp != null && temp.getRightCell() != null) {
                        position = findCell(temp.getRightCell().getCellId() + 4);
                        if (position == positionToAttack.getCurrentcell())
                            return true;
                    } else
                        break;
                }
            }
        }
        return false;
    }

    /**
     * Function find a cell ID
     * @param ID cell id
     * @return cell
     */
    private Cell findCell(int ID){
        for(int i = 0; i < map.getRoomList().size(); i++){
            for(int j = 0; j < map.getRoomList().get(i).getCellsList().size(); j++){
                if(map.getRoomList().get(i).getCellsList().get(j).getCellId() == ID)
                    return map.getRoomList().get(i).getCellsList().get(j);
            }
        }
        return null;
    }
}