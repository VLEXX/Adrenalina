package view.viewstatesrmi;

import model.datapacket.DataPacket;
import model.munitions.Munitions;
import model.playerdata.Player;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewShootThirdStateRMI implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        if(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().hasThirdAttack()==true){
            int red, blue, yellow;
            red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getThirdPrice().get(Munitions.RED);
            blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getThirdPrice().get(Munitions.BLUE);
            yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getThirdPrice().get(Munitions.YELLOW);
            if((red>=0)&&(blue>=0)&&(yellow>=0)){
                System.out.println("Do you want to use weapon third effect? ( Y | N )\n");
                String s = stdin.nextLine();
                s.toLowerCase();
                if(s.equals("y")){
                    DataPacket dataPacket = new DataPacket();
                    dataPacket.setWeapon(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks());
                    dataPacket.setThirdAttack(true);
                    return dataPacket;
                }
            }
        }
        return new DataPacket();
    }
}
