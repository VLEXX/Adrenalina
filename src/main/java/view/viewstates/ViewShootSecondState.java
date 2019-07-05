package view.viewstates;

import model.datapacket.DataPacket;
import model.munitions.Munitions;
import model.playerdata.Player;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewShootSecondState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        if(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().hasSecondAttack()==true){
            int red, blue, yellow;
            if(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getSecondPrice().get(Munitions.RED)!=null) {
                red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getSecondPrice().get(Munitions.RED);
            }
            else{
                red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED);
            }
            if(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getSecondPrice().get(Munitions.BLUE)!=null) {
                blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getSecondPrice().get(Munitions.BLUE);
            }
            else{
                blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE);
            }
            if(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getSecondPrice().get(Munitions.YELLOW)!=null) {
                yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks().getSecondPrice().get(Munitions.YELLOW);
            }
            else {
                yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW);
            }
            if((red>=0)&&(blue>=0)&&(yellow>=0)){
                System.out.println("Do you want to use weapon second effect? ( Y | N )\n");
                String s = stdin.nextLine();
                s.toLowerCase();
                if(s.equals("y")){
                    DataPacket dataPacket = new DataPacket();
                    dataPacket.setToken(viewDatabase.getClientToken());
                    dataPacket.setWeapon(viewDatabase.getViewCurrentPlayerState().getWeaponMultAttacks());
                    dataPacket.setSecondAttack(true);
                    return dataPacket;
                }
            }
        }
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        dataPacket.setPlayer(player);
        return dataPacket;
    }
}
