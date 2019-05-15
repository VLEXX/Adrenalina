/**
 * @author: Alex Saletti
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.gamedata.ChartScore;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.map.*;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.DamageBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class EndTurnState implements State {

    private InitializeAllPlay allPlay;

    public EndTurnState(InitializeAllPlay initializeAllPlay){
        this.allPlay = initializeAllPlay;
    }

    /**
     * manages the end of the turn refilling the map, recharging weapons selected by the player and
     * update the scoreboard
     * @param dataPacket class containing necessary parameters sent by client
     * @return a message saying if the required action was successful or not
     */
    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        this.refillMap(allPlay);
        MessageEnum out1 = this.rechargeWeapons(allPlay,dataPacket);
        if(out1 == MessageEnum.AMMO_ERROR || out1 == MessageEnum.TOOMUCH_POWERUPS)
            return out1;
        this.scoreCounter(allPlay);
        return MessageEnum.OK;
    }


    /**
     * refills the empty ammo and weapons slots of the map
     * @param i class containing information about the current match
     */
    private void refillMap( InitializeAllPlay i){
        for(Room r : i.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell c : r.getCellsList()){
                if(c.getSpawnpointzone()==null){
                    if(c.getAmmohere()==null)
                        c.setAmmohere(i.getCurrentDeckState().getAmmodeck().pop());
                }
                else{
                    for(int j=0;j<3;j++){
                        if(c.getSpawnpointzone().getSpawnWeaponsList()[j]==null) {
                            if(!i.getCurrentDeckState().getWeaponsdeck().empty())
                                c.getSpawnpointzone().getSpawnWeaponsList()[j] = i.getCurrentDeckState().getWeaponsdeck().pop();
                        }
                    }
                }
            }
        }
    }


    /**
     * recharges weapons selected by the player, returns error if the player
     * hasn't enough ammo or gives more payingpowerups than the needed ones
     * @param i class containing information about the current match
     * @param d class containing necessary parameters sent by client
     * @return a message saying if the required action was successful or not
     */
    private MessageEnum rechargeWeapons(InitializeAllPlay i, DataPacket d){
        HashMap<Munitions, Integer> cost = new HashMap<>();
        cost.put(Munitions.RED, 0);
        cost.put(Munitions.YELLOW, 0);
        cost.put(Munitions.BLUE, 0);
        for(Weapon w : d.getWeaponsToBeRecharged()){
            if(w.getFirstPrice().containsKey(Munitions.RED))
                cost.put(Munitions.RED,cost.get(Munitions.RED)+w.getFirstPrice().get(Munitions.RED));
            if(w.getFirstPrice().containsKey(Munitions.YELLOW))
                cost.put(Munitions.YELLOW,cost.get(Munitions.YELLOW)+w.getFirstPrice().get(Munitions.YELLOW));
            if(w.getFirstPrice().containsKey(Munitions.BLUE))
                cost.put(Munitions.BLUE,cost.get(Munitions.BLUE)+w.getFirstPrice().get(Munitions.BLUE));
        }
        for(PowerUp pw : d.getPaymentPowerUp()){
            cost.put(Munitions.RED,cost.get(Munitions.RED)-pw.munitionsChecker(Munitions.RED));
            cost.put(Munitions.YELLOW,cost.get(Munitions.YELLOW)-pw.munitionsChecker(Munitions.YELLOW));
            cost.put(Munitions.BLUE, cost.get(Munitions.BLUE)-pw.munitionsChecker(Munitions.BLUE));
        }
        if(cost.get(Munitions.RED)<0 || cost.get(Munitions.YELLOW)<0 || cost.get(Munitions.BLUE)<0)
            return MessageEnum.TOOMUCH_POWERUPS;
        if(i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED)-cost.get(Munitions.RED)<0 || i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW)-cost.get(Munitions.YELLOW)<0 || i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE)-cost.get(Munitions.BLUE)<0)
            return MessageEnum.AMMO_ERROR;
        else{
            i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.RED,i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED)-cost.get(Munitions.RED));
            i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.YELLOW,i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW)-cost.get(Munitions.YELLOW));
            i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.BLUE,i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE)-cost.get(Munitions.BLUE));
            return MessageEnum.OK;
        }

    }


    /**
     * updates all players'score and the chart; if a player is dead the respective MaxPointIndex will be updated
     * @param i class containing information about the current match
     */
    private void scoreCounter(InitializeAllPlay i){
        HashMap<Player,Integer> score = new HashMap<>();
        i.getCurrentPlayerState().forEach(((player, currentPlayerState) -> score.put(player,0)));
        i.getCurrentPlayerState().forEach(((player, currentPlayerState) -> {
            if(currentPlayerState.getBoard().getDamageBox().isDead()) {
                DamageBox db = currentPlayerState.getBoard().getDamageBox();
                score.put(db.getDamage()[0],score.get(db.getDamage()[0])+1);
                Player[] points=this.damageScoreBoard(db.getDamage());
                for(int j=0; j<points.length;j++){
                score.put(points[j],score.get(points[j])+db.getMaxPointArray()[db.getMaxPointIndex()+j]);
                }
                db.setMaxPointIndex(db.getMaxPointIndex()+1);
            }
        }));
        score.forEach(((player, integer) -> {
            i.getChartScore().setScore(player,integer);
        }));

    }

    /**
     * returns the sorted turn score chart counting damage done by all players to the dead ones
     * @param p array containing players'damages
     * @return
     */
    private Player[] damageScoreBoard(Player[] p){
        HashMap<Player,Integer> dc = new HashMap<>();
        ArrayList<Player> order = new ArrayList<>();
        for(int j=0;p[j]!=null;j++){
            if (!dc.containsKey(p[j])) {
                dc.put(p[j], 1);
                order.add(p[j]);
            } else
                dc.put(p[j], dc.get(p[j]) + 1);
        }
        int k = dc.size();
        Player[] result = new Player[k];
        for(int j=0;j<k;j++){
            for(int y=0;y<j;y++){
              if(dc.get(result[y])<dc.get(result[y+1]) || (dc.get(result[y])==dc.get(result[y+1])&& order.indexOf(result[y])>order.indexOf(result[y+1]))){
                  Player temp = result[y+1];
                  result[y+1] = result[y];
                  result[y]=temp;
              }

            }
        }
        return result;
    }
}
