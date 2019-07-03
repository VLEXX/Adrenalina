/**
 * @author Alex Saletti
 */
package view;

import model.map.Cell;
import model.map.Room;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.util.HashMap;

/**
 * it gives the player necessary in-game information
 */
public class PlayerInformer {
    ViewDatabase dbb;

    /**
     * builder
     * @param dbb contains match data
     */
    public PlayerInformer(ViewDatabase dbb){
        this.dbb=dbb;
    }

    public void informer(){
        mapStats();
        positionsInformer();
        playerStats();
    }



    /**
     * it gives the players information about players'position
     */
    public void positionsInformer(){
        System.out.println("PLAYERS' POSITION:\n");
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



    /**
     * it gives the players information about their equipment, their received damage points and other player damage points
     */
    public void playerStats(){
        System.out.println("YOUR EQUIPMENT:\n");
        if(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList().isEmpty())
            System.out.println("You have no weapons.\n");
        else{
            System.out.println("Owned weapons:\n ");
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
        System.out.println("DAMAGE STATS:\n");
        if (dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[0]==null)
            System.out.println("You haven't got any damage yet.\n\n");
        else{
            HashMap<Player,Integer> dcount = new HashMap<>();
            int kl=0;
            for(kl =0; dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[kl]!=null;kl++){
                dcount.putIfAbsent(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[kl],0);
                dcount.put(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[kl],dcount.get(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[kl])+1);
            }
            if(kl<11)
                System.out.println("You got "+kl+" damage points.\n");
            else if(kl==11) {
                if(dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[10]==dbb.getThisplayer())
                    System.out.println("You were killed (11 damage points) by a spawnpoint.\n");
                else
                    System.out.println("You were killed (11 damage points) by " + dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[10]+".\n");
            } else if(kl>11)
                System.out.println("You were overkilled (12 damage points) by "+ dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getDamageBox().getDamage()[11]+".\n");
            dcount.forEach((player, integer) -> {
                if(player==dbb.getThisplayer())
                    System.out.println("You got "+integer+" damage points from spawnpoints.\n");
                else
                    System.out.println("You got "+integer+" damage points from "+player+".\n");
            });
            if(!dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMarksBox().getMyMarksMap().isEmpty()) {
                dbb.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getMarksBox().getMyMarksMap().forEach((player, integer) -> {
                    System.out.println("You got " + integer + " marks from " + player + " player.\n");;
                });
            }else
                System.out.println("You got no marks.");
            System.out.println("\n");
        }
        dbb.getPlayersdamage().forEach((player, players) -> {
            if(player!=dbb.getThisplayer()) {
                if (players[0] == null)
                    System.out.println(player + " player hasn't got any damage yet.\n\n");
                else {
                    HashMap<Player, Integer> dcount = new HashMap<>();
                    int kl = 0;
                    for (kl = 0; players[kl] != null; kl++) {
                        dcount.putIfAbsent(players[kl], 0);
                        dcount.put(players[kl], dcount.get(players[kl]) + 1);
                    }
                    if (kl < 11)
                        System.out.println(player + " player got " + kl + " damage points.\n");
                    else if (kl == 11) {
                        if (players[10] == player)
                            System.out.println(player + " player was killed (11 damage points) by a spawnpoint.\n");
                        else
                            System.out.println(player + " player was killed (11 damage points) by " + players[10] + ".\n");
                    } else if (kl > 11)
                        System.out.println(player + " player was overkilled (12 damage points) by " + players[11] + ".\n");
                    dcount.forEach((pplayer, integer) -> {
                        if (pplayer == player)
                            System.out.println(player + " player got " + integer + " damage points from spawnpoints.\n");
                        else
                            System.out.println(player + " player got " + integer + " damage points from " + pplayer + ".\n");
                    });
                        if (!dbb.getPlayersmarks().get(player).isEmpty()) {
                            dbb.getPlayersmarks().get(player).forEach((player2, integer) -> {
                                System.out.println(player + " got " + integer + " marks from " + player2 + " player.\n");
                                ;
                            });
                        } else
                            System.out.println(player + " got no marks.\n");
                    System.out.println("\n");
                }
            }});

    }



    /**
     * it shows all the available pickupable on the map
     */
    public void mapStats(){
        System.out.println("MAP STATS\n");
        for(Room r : dbb.getViewMapState().getSelectedMap().getRoomList()){
            for(Cell c : r.getCellsList()){
                System.out.println("The cell having ID = "+c.getCellId()+" contains ");
                if(c.getAmmohere()!=null) {
                    System.out.println("an ammo card having " + c.getAmmohere().getAmmoList()[0] + " red munitions, " + c.getAmmohere().getAmmoList()[1] + " yellow munitions and " + c.getAmmohere().getAmmoList()[2] + " blue munitions;");
                    if(c.getAmmohere().getPossiblePowerUp())
                        System.out.println("it also allows you to draw a powerup from the deck.\n");
                    else
                        System.out.print("\n");
                }else if(c.getSpawnpointzone()!=null){
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
                }else
                    System.out.println("nothing.\n\n");
            }
        }
    }



    /**
     * it shows stats about the match (final frenzy mode and number of left skulls)
     */
    public void matchStats(){
        System.out.println("MATCH STATS\n");
        short i=0;
        for(short j=0;j<dbb.getSkullArray().length;j++){
            if(dbb.getSkullArray()[j]==null)
                i++;
        }
        if(i>1)
            System.out.println(i+"skulls left to the end of the game.\n\n");
        else if (i==1)
            System.out.println("Only 1 skull left to the end of the game!!\n\n");
        if(dbb.getFinalFrenzy())
            System.out.println("Final Frenzy is enabled!!!!\n\n");


    }
}