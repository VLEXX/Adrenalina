package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import model.weaponscard.Weapon;
import view.ViewDatabase;
import java.util.Scanner;

public class ViewShootFirstState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        System.out.println("Choose a weapon to use between: \n");
        for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
            System.out.println(weapon.getName() + " | ");
        }
        String s;
        DataPacket dataPacket = new DataPacket();
        Weapon w;
        while(true){
            s = stdin.nextLine();
            s.toLowerCase();
            for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
                if(s.equals(weapon.getName())){
                    w = weapon;
                    viewDatabase.getViewCurrentPlayerState().setWeaponMultAttacks(w);
                    dataPacket.setPlayer(player);
                    dataPacket.setWeapon(w);
                    return dataPacket;
                }
            }
            System.out.println("WRONG INPUT! Please choose a weapon between: \n");
            for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
                System.out.println(weapon.getName() + " | ");
            }
        }
    }
}
