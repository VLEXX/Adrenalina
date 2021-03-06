/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.MessageEnum;

/**
 * Class with method that write to System.out messages received by the server
 */
public class MessageWriter {

    public void writeMessage(MessageEnum messageEnum){

        if(messageEnum.equals(MessageEnum.UNREACHABLE_CELL)){
            System.out.println(MessageEnum.UNREACHABLE_CELL);
        }
        if(messageEnum.equals(MessageEnum.AMMO_ERROR)){
            System.out.println(MessageEnum.AMMO_ERROR);
        }
        if(messageEnum.equals(MessageEnum.POWERUP_NOT_FOUND)){
            System.out.println("You don't have Power-Up to use!");
        }
        if(messageEnum.equals(MessageEnum.WEAPON_ERROR_2)){
            System.out.println(MessageEnum.WEAPON_ERROR_2);
        }
        if(messageEnum.equals(MessageEnum.NOT_YOUR_TURN)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.ACTION_ERROR)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.EMPTY_WEAPON)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.WEAPON_NOT_FOUND)){
            System.out.println(MessageEnum.WEAPON_NOT_FOUND);
        }
        if(messageEnum.equals(MessageEnum.POSITION_NOT_FOUND)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.PLAYER_TOO_MUCH_NEAR)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.PLAYER_UNREACHABLE)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.CANNOT_USE_THIS_EFFECT)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.POSITION_UNREACHABLE)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.PLAYER_NOT_FOUND)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.PLAYERS_NOT_VALID)){
            System.out.println(MessageEnum.PLAYER_NOT_VALID);
        }
        if(messageEnum.equals(MessageEnum.PLAYER_ALREADY_PRESENT)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.ATTACK_NOT_PRESENT)){
            System.out.println(" ");
        }
        if(messageEnum.equals(MessageEnum.TOO_MUCH_POWERUPS)){
            System.out.println(MessageEnum.TOO_MUCH_POWERUPS);
        }
        if(messageEnum.equals(MessageEnum.TOKEN_ERROR)){
            System.out.println(MessageEnum.TOKEN_ERROR);
        }
        if(messageEnum.equals(MessageEnum.OK)){
            System.out.println(MessageEnum.OK);
        }
    }
}
