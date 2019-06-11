package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewPowerupState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        String s;
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        dataPacket.setPlayer(player);
        if(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().isEmpty()){
            return dataPacket;
        }
        for(PowerUp powerUp: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
            if(powerUp.getId().equals(PowerUpId.NEWTON)|| powerUp.getId().equals(PowerUpId.TELEPORTER)){
                System.out.println("Choose Power-Up to use between:\n");
                if(powerUp.getId().equals(PowerUpId.NEWTON)){
                    System.out.println(PowerUpId.NEWTON + " " + powerUp.getColor() + "\n");
                }
                else if(powerUp.getId().equals(PowerUpId.TELEPORTER)){
                       System.out.println(PowerUpId.TELEPORTER + " " + powerUp.getColor() + "\n");
                }
            }
        }

        if(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().contains(PowerUpId.TELEPORTER)){
            System.out.println("");

            return dataPacket;
        }
        for(PowerUp powerUp: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()) {
            if (powerUp.getId().equals(PowerUpId.TAGBACK_GRENADE)){
                System.out.println("Do you want to use PowerUp 'TAGBACK GRANADE'? ( Y | N )\n");
                s = stdin.nextLine();
                s.toLowerCase();
                if(s.equals("y")){
                    dataPacket.getMarksToAdd().put(player,1);
                    return dataPacket;
                }
            }
        }
        return null;
    }
}
