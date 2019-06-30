/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.Position;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

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
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 2);
        super.setName("machinegun");
    }

    /**
     * Attack for the DOMINATION mode at the spawn point
     * @param myPlayer player who attack
     * @param spawnPoint
     * @param allPlay current state game
     * @return OK or ATTACK_NOT_PRESENT or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, SpawnPoint spawnPoint, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        int cellID = myPosition.getCurrentcell().getCellId();
        int roomID = myPosition.getCurrentroom().getRoomId();
        if(allPlay.getStateSelectedMode().getSelectedmode() == Mode.DOMINATION) {
            if (allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(roomID-1).getCellsList().get(cellID-1).getSpawnpointzone() == spawnPoint) {
                spawnPoint.getSPDamage().add(myPlayer);
            } else
                return MessageEnum.POSITION_NOT_VALID;
            return MessageEnum.OK;
        } else
            return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function first attack
     *
     * @param player            player who attack
     * @param playerToAttack    player to attack
     * @param positionToMove null
     * @param allPlay           current state game
     * @return OK or POSITION_NOT_FOUND
     */
    public MessageEnum firstAttack(Player player, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control1 = 0;
        int control2 = 0;
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        Position positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition();
        player1 = playerToAttack.get(0);
        player2 = playerToAttack.get(1);
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(player))
            control1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(player);
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().containsKey(player))
            control2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().get(player);
        if(playerToAttack.get(0) != null && check(position, positionToAttack1) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(playerToAttack.get(1) != null && check(position, positionToAttack2) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if (positionToAttack1 != null && playerToAttack.get(0) != null) {
            if(control1 != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control1, player);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
        }
        if (positionToAttack2 != null && playerToAttack.get(1) != null) {
            if(control2 != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control2, player);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(1, player);
        }
        return MessageEnum.OK;
    }

    /**
     * Function focused shot
     *
     * @param player           player who attack
     * @param playerToAttack   player to attack
     * @param positionToMove null
     * @param allPlay current state game
     * @return OK or CANNOT_USE_THIS_EFFECT or POSITION_NOT_FOUND
     */
    public MessageEnum secondAttack(Player player, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay) {
        if((playerToAttack.isEmpty()==true)) {
            return MessageEnum.OK;
        }

        int control = 0;
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if (check(position, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if (player1 == null || player2 == null)
            return MessageEnum.CANNOT_USE_THIS_EFFECT;
        if (allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getActiveplayer() != player1 && allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getActiveplayer() != player2) {
            if(control != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
        }
        else return MessageEnum.CANNOT_USE_THIS_EFFECT;
        if (playerToAttack.get(0) == player1)
            player1Attacked = true;
        else player1Attacked = false;
        return MessageEnum.OK;
    }

    /**
     * Function support tripods
     *
     * @param player            player who attack
     * @param playerToAttack    first player to firstAttack
     * @param allPlay current state game
     * @return OK or CANNOT_USE_THIS_EFFECT or POSITION_NOT_FOUND or WEAPON_ERROR
     */
    public MessageEnum thirdAttack(Player player, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        if((playerToAttack.isEmpty()==true)) {
            return MessageEnum.OK;
        }

        if((playerToAttack.get(0).equals(Player.FLAG))) {
            return MessageEnum.WEAPON_ERROR;
        }

        int control = 0;
        int control1 = 0;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(player))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(player);
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().containsKey(player))
            control1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().get(player);
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        Position positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition();
        if(check(position, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(check(position, positionToAttack2) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if (player1 == null || player2 == null) {
            if (playerToAttack.get(0) == player1 || playerToAttack.get(0) == player2) {
                if(control != 0) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
                }
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
                if (playerToAttack.get(1) != null) {
                    if(control1 != 0) {
                        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, player);
                    }
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(1, player);
                }
            } else if (playerToAttack.get(1) == player1 || playerToAttack.get(1) == player2) {
                if(control1 != 0) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, player);
                }
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(1, player);
                if (playerToAttack.get(0) != null) {
                    if(control != 0) {
                        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
                    }
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
                }
            }
        } else if ((playerToAttack.get(0) == player1 && player1Attacked == false) || (playerToAttack.get(0) == player2 && player1Attacked == true)) {
            if(control != 0) {
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
            }
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
            if (playerToAttack.get(1) != null && playerToAttack.get(1) != player1 && playerToAttack.get(1) != player2) {
                if(control1 != 0) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, player);
                }
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(1, player);
            }
        } else if ((playerToAttack.get(1) == player1 && player1Attacked == false) || (playerToAttack.get(1) == player2 && player1Attacked == true)){
            if(control1 != 0) {
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, player);
            }
            allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(1, player);
            if (playerToAttack.get(0) != null && playerToAttack.get(0) != player1 && playerToAttack.get(0) != player2) {
                if (control != 0) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
                }
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
            }
        } else return MessageEnum.CANNOT_USE_THIS_EFFECT;
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position
     *
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     */
    private boolean check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getVisibleCells().size(); i++) {
            if (myPosition.getCurrentcell().getVisibleCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            return false;
        }
        return true;
    }
}