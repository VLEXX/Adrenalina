/**
 * @author Alex Saletti
 */
package view;

import model.map.Cell;
import model.map.Room;
import model.munitions.Munitions;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

public class PlayerInformer {
    ViewDatabase dbb;

    //costruttore
    public PlayerInformer(ViewDatabase dbb){
        this.dbb=dbb;
    }

    public void informer(){
        positionsInformer();
        playerStats();
        mapStats();
    }

    //informa delle posizioni
    public void positionsInformer(){
        if(dbb.getViewPlayerPosition().getCurrentcell()==null) {
            if (dbb.getSkullArray()[0] != null) {
                System.out.println("You are dead and will respawn at your turn;\n");
            }
        }
        else
            System.out.println("You are in the cell having ID = "+dbb.getViewPlayerPosition().getCurrentcell().getCellId()+" (room "+dbb.getViewPlayerPosition().getCurrentroom().getRoomId()+");\n");
        dbb.getPositionHashMap().forEach((player, position) -> {
            if(player!=dbb.getThisplayer()) {
                if (dbb.getPositionHashMap().get(player) != null) {
                    if(!player.equals(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getActiveplayer())) {
                        System.out.println("\nThe " + player + " player is in the cell having ID=" + position.getCurrentcell().getCellId() + " (room ID=" + position.getCurrentroom().getRoomId() + ");\n");
                    }
                }
                else {
                    if(dbb.getSkullArray()[0]!=null) {
                        System.out.println("The " + player + " is dead;\n");
                    }
                }
            }
        });



    }

    //mostra al player le proprie statistiche
    public void playerStats(){
        System.out.println("YOUR EQUIPMENT:\n");
        if(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList().isEmpty())
            System.out.println("You have no weapons.\n");
        else{
            System.out.println("Weapons: \n");
            for(Weapon w : dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
                System.out.print(w.getName());
                if(w.getLoaded())
                    System.out.print(" (LOADED)\n");
                else
                    System.out.print(" (UNLOADED)\n");
                if(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList().indexOf(w)==dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList().size()-1)
                    System.out.print("\n");
                else
                    System.out.print("\n");
            }
        }
        if(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().isEmpty())
            System.out.println("You have no powerups.\n");
        else{
            System.out.println("Powerups:");
            for(PowerUp w : dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                System.out.print(w.getColor()+" "+w.getId());
                if(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().indexOf(w)==dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().size()-1)
                    System.out.print(".\n\n");
                else
                    System.out.print(", ");
            }
        }
        System.out.println("You have:");
        System.out.print(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) + " " + "YELLOW munitions, ");
        System.out.print(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) + " " + "BLUE munitions, ");
        System.out.print(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) + " " + "RED munitions.\n\n");
    }

    //mostra tutto quello presente sulla mappa
    public void mapStats(){
        for(Room r : dbb.getViewMapState().getSelectedMap().getRoomList()){
            for(Cell c : r.getCellsList()){
                System.out.println("The cell having ID = "+c.getCellId()+" contains ");
                if(c.getAmmohere()!=null) {
                    System.out.println("an ammo card having " + c.getAmmohere().getAmmoList()[0] + " red munitions, " + c.getAmmohere().getAmmoList()[1] + " yellow munitions and " + c.getAmmohere().getAmmoList()[2] + " blue munitions;");
                    if(c.getAmmohere().getPossiblePowerUp())
                        System.out.println("it also allows you to draw a powerup from the deck.\n");
                    else
                        System.out.print("\n");
                }else{
                    if(c.getSpawnpointzone().getSpawnWeaponsList()[0]==null && c.getSpawnpointzone().getSpawnWeaponsList()[1]==null && c.getSpawnpointzone().getSpawnWeaponsList()[2]==null)
                        System.out.println("nothing.\n");
                    else{
                        for(int z=0; z<3;z++){
                            if(c.getSpawnpointzone().getSpawnWeaponsList()[z]!=null) {
                                System.out.print(c.getSpawnpointzone().getSpawnWeaponsList()[z].getName());
                                if(z==2)
                                    System.out.print(".\n\n");
                                else
                                    System.out.print(", ");
                            }
                        }
                    }
                }
            }
        }
    }
}