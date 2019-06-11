package view.viewstates;

import model.datapacket.DataPacket;
import model.map.Cell;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.weaponscard.Weapon;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewPickupState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        dataPacket.setPlayer(player);
        System.out.println("Do you want to move before? ( Y | N )\n");
        String s = stdin.nextLine();
        s.toLowerCase();
        Cell tempcell = null;
        if (s.equals("y")) {
            if(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().isPickUp()==false) {
                System.out.println("Write the cell ID between: \n");
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell() != null) {
                    System.out.print(viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell().getCellId() + " ");
                }
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell() != null) {
                    System.out.print(viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell().getCellId() + " ");
                }
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell() != null) {
                    System.out.print(viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell().getCellId() + " ");
                }
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell() != null) {
                    System.out.print(viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell().getCellId() + " ");
                }
                System.out.println("\n");
                int i = stdin.nextInt();
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell() != null) {
                    if (i == viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell().getCellId()) {
                        tempcell = viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell();
                        dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getUpCell());
                    }
                }
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell() != null) {
                    if (i == viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell().getCellId()) {
                        tempcell = viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell();
                        dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getDownCell());
                    }
                }
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell() != null) {
                    if (i == viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell().getCellId()) {
                        tempcell = viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell();
                        dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getRightCell());
                    }
                }
                if (viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell() != null) {
                    if (i == viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell().getCellId()) {
                        tempcell = viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell();
                        dataPacket.setCell(viewDatabase.getViewPlayerPosition().getCurrentcell().getLeftCell());
                    }
                }
            }
            else{
                System.out.println("Write the cell ID between: \n");
                for(Cell cell: viewDatabase.getViewPlayerPosition().getCurrentcell().getReachable2Cells()){
                    System.out.print(cell.getCellId() + " | ");
                }
                int i = stdin.nextInt();
                for(Cell cell: viewDatabase.getViewPlayerPosition().getCurrentcell().getReachable2Cells()){
                    if(i==cell.getCellId()){
                        tempcell = cell;
                        dataPacket.setCell(cell);
                        break;
                    }
                }
            }
        }
        if(tempcell==null){
            tempcell=viewDatabase.getViewPlayerPosition().getCurrentcell();
        }
        if (tempcell.getSpawnpointzone() != null) {
            if ((tempcell.getSpawnpointzone().getSpawnWeaponsList()[0]==null)||(tempcell.getSpawnpointzone().getSpawnWeaponsList()[1]==null)||(tempcell.getSpawnpointzone().getSpawnWeaponsList()[2]==null)) {
                System.out.println("Weapon List in this SpawnPoint is empty!\n");
                dataPacket.setWeaponlistempty(true);
                return dataPacket;
            }
            else {
                System.out.println("What do you want to pickup between: \n");
                for (Weapon weapon : tempcell.getSpawnpointzone().getSpawnWeaponsList()) {
                    System.out.print(weapon.getName() + " | ");
                }
                System.out.print("\n");
                while (true) {
                    Weapon w = null;
                    String s1 = stdin.nextLine();
                    s1.toLowerCase();
                    for (Weapon weapon : tempcell.getSpawnpointzone().getSpawnWeaponsList()){
                        if (s1.equals(weapon.getName())){
                            w = weapon;
                            int red = 0;
                            int blue = 0;
                            int yellow = 0;
                            if (weapon.getCardColor().equals(Munitions.BLUE)) {
                                if(weapon.getFirstPrice().containsKey(Munitions.RED)==true) {
                                    red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED);
                                }
                                if(weapon.getFirstPrice().containsKey(Munitions.BLUE)==true) {
                                    blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE) + 1;
                                }
                                if(weapon.getFirstPrice().containsKey(Munitions.YELLOW)==true) {
                                    yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW);
                                }
                            }
                            if (weapon.getCardColor().equals(Munitions.RED)) {
                                if(weapon.getFirstPrice().containsKey(Munitions.RED)==true) {
                                    red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED) + 1;
                                }
                                if(weapon.getFirstPrice().containsKey(Munitions.BLUE)==true) {
                                    blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE);
                                }
                                if(weapon.getFirstPrice().containsKey(Munitions.YELLOW)==true) {
                                    yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW);
                                }
                            }
                            if (weapon.getCardColor().equals(Munitions.YELLOW)) {
                                if(weapon.getFirstPrice().containsKey(Munitions.RED)==true) {
                                    red = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - weapon.getFirstPrice().get(Munitions.RED);
                                }
                                if(weapon.getFirstPrice().containsKey(Munitions.BLUE)==true) {
                                    blue = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - weapon.getFirstPrice().get(Munitions.BLUE);
                                }
                                if(weapon.getFirstPrice().containsKey(Munitions.YELLOW)==true) {
                                    yellow = viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - weapon.getFirstPrice().get(Munitions.YELLOW) + 1;
                                }
                            }
                            if ((red >= 0) && (blue >= 0) && (yellow >= 0)) {
                                dataPacket.setWeapon(weapon);
                                return dataPacket;
                            } else {
                                System.out.println("Insufficient Munitions!\n");
                                break;
                            }
                        }
                    }
                    if (w == null) {
                        System.out.println("WRONG INPUT! Please choose between: \n");
                        for (Weapon weapon1 : tempcell.getSpawnpointzone().getSpawnWeaponsList()) {
                            System.out.print(weapon1.getName() + " | ");
                        }
                    }
                }
            }
        }
        System.out.println("No weapon found in this cell!\n" + stdin.nextLine());
        System.out.println("\nPlease choose a valide ACTION...\n");
        dataPacket.setWeaponlistempty(true);
        return dataPacket;
    }
}
