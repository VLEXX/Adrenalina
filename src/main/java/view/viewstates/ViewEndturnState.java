package view.viewstates;

import model.datapacket.DataPacket;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;
import view.ViewDatabase;

import java.util.Scanner;

public class ViewEndturnState implements ViewState{

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {

        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
        System.out.println("Do you want recharge a weapon? ( Y | N )\n");
        String s = stdin.nextLine();
        s.toLowerCase();
        if(s.equals("y")){
            System.out.println("Which weapon do you want to recharge between: \n");
            for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
                System.out.print(weapon.getName() + " | ");
            }
            while(true) {
                s = stdin.nextLine();
                s.toLowerCase();
                for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()) {
                    if (s.equals(weapon.getName())) {
                        int blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE);
                        int red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED);
                        int yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW);
                        if ((blue >= 0) && (red >= 0) && (yellow >= 0)) {
                            dataPacket.getWeaponsToBeRecharged().add(weapon);
                            return dataPacket;
                        }
                        else{
                            System.out.println("Insufficient munition!\nDo you want to pay with a Power-Up as munition? ( Y | N )\n");
                            while ((true)){
                                s = stdin.nextLine();
                                s.toLowerCase();
                                if(s.equals("y")){
                                    int l = 0;
                                    System.out.println("Which Power-Up do you want to use between: \n");
                                    for(PowerUp powerUp: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                                        System.out.println(powerUp.getId() + " " + powerUp.getColor());
                                    }
                                    System.out.println("Write the Power-Up name...\n");
                                    while(true){
                                        s = stdin.nextLine();
                                        s.toUpperCase();
                                        for(PowerUp powerUp: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                                            if(s.equals(powerUp.getId())){
                                                int i=0;
                                                for(PowerUp powerUp1: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                                                    if(s.equals(powerUp1.getId())){
                                                        i++;
                                                    }
                                                }
                                                if(i==1){
                                                    dataPacket.getPaymentPowerUp().add(powerUp);
                                                    l++;
                                                    break;
                                                }
                                                if(i>1) {
                                                    System.out.println("Write the Power-Up color...");
                                                    while (true) {
                                                        s = stdin.nextLine();
                                                        s.toUpperCase();
                                                        PowerUp control = null;
                                                        for (PowerUp powerUp1 : viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()) {
                                                            if (s.equals(powerUp1.getColor())) {
                                                                control = powerUp1;
                                                                dataPacket.getPaymentPowerUp().add(powerUp1);
                                                                break;
                                                            }
                                                        }
                                                        if(control==null){
                                                            System.out.println("WRONG INPUT! Please choose a valide Power-Up color...\n");
                                                        }
                                                        else {
                                                            l++;
                                                            break;
                                                        }
                                                    }
                                                }
                                                if(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().size()>l) {
                                                    System.out.println("Do you want to pay with another Power-Up?");
                                                    while(true){
                                                        s=stdin.nextLine();
                                                        s.toLowerCase();
                                                        if(s.equals("y")){
                                                            break;
                                                        }
                                                        if(s.equals("n")){
                                                            return dataPacket;
                                                        }
                                                        else{
                                                            System.out.println("WRONG INPUT! Please write 'y' or 'n'...\n");
                                                        }
                                                    }
                                                }
                                                if(l==viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().size()){
                                                    return dataPacket;
                                                }
                                            }
                                        }
                                    }
                                }
                                if(s.equals("n")){
                                    System.out.println("Weapon recharge canceled!\n");
                                    return dataPacket;
                                }
                                else{
                                    System.out.println("WRONG INPUT! Please write 'y' or 'n'...\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        return dataPacket;
    }
}
