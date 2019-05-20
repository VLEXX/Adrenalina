package view.viewstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.weaponscard.Weapon;
import view.ViewDatabase;

import java.util.Scanner;

public class ViewPickupState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
        System.out.println("Do you want to move before? ( Y | N )\n");
        String s = stdin.nextLine();
        s.toLowerCase();
        if(s.equals("y")){
            System.out.println("Write the cell ID between: \n");
            System.out.println(viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell().getCellId() + " | " + viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell().getCellId() + " | " + viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell().getCellId() + " | " + viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell().getCellId());
            int i = stdin.nextInt();
            if(i == viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell().getCellId()){
                dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell());
            }
            if(i == viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell().getCellId()){
                dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell());
            }
            if(i == viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell().getCellId()){
                dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell());
            }
            if(i == viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell().getCellId()){
                dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell());
            }
        }
        if(viewDatabase.getViewPlayerPosition().getCurrentcell().getSpawnpointzone()!=null) {
            System.out.println("What do you want to pickup between: \n");
            for(Weapon weapon: viewDatabase.getViewPlayerPosition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()){
                System.out.println(weapon.getName() + " ");
            }
            while(true) {
                Weapon w = null;
                String s1 = stdin.nextLine();
                s1.toLowerCase();
                for (Weapon weapon : viewDatabase.getViewPlayerPosition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()) {
                    if (s1.equals(weapon.getName())) {
                        w=weapon;
                        int red = 0;
                        int blue = 0;
                        int yellow = 0;
                        if(weapon.getCardColor().equals(Munitions.BLUE)) {
                            red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED);
                            blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE) + 1;
                            yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW);
                        }
                        if(weapon.getCardColor().equals(Munitions.RED)) {
                            red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED) + 1;
                            blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE);
                            yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW);
                        }
                        if(weapon.getCardColor().equals(Munitions.YELLOW)) {
                            red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED);
                            blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE);
                            yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW) +1;
                        }
                        if((red>=0)&&(blue>=0)&&(yellow>=0)) {
                            if(dataPacket.getWeapon()!=null){
                                dataPacket.setWeapon(weapon);
                                return dataPacket;
                            }
                        }
                        else{
                            System.out.println("Insufficient Munitions!\n");
                            break;
                        }
                    }
                }
                if(w==null) {
                    System.out.println("WRONG INPUT! Please choose between: \n");
                    for (Weapon weapon1 : viewDatabase.getViewPlayerPosition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()) {
                        System.out.println(weapon1.getName() + " ");
                    }
                }
            }
        }
        return null;
    }
}
