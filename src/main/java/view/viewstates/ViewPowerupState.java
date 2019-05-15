package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import view.ViewDatabase;

import java.util.Scanner;

public class ViewPowerupState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        String s;
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
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
