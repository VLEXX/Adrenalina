/**
 * @author Alex Saletti
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.gamedata.Mode;
import model.map.*;
import model.munitions.Munitions;
import model.playerdata.DamageBox;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class EndTurnState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;
    private IDClientList idClientList;

    public EndTurnState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap, IDClientList clientList)throws RemoteException{
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.END;
        this.idClientList=clientList;
    }

    public StatesEnum getNamestate() throws RemoteException {
        return namestate;
    }

    /**
     * manages the end of the turn refilling the map, recharging weapons previously selected by the player and
     * updating the scoreboard
     * @param dataPacket class containing necessary parameters sent by client
     * @return a message saying if the required action was successful or not
     */
    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException {
        if(!(idClientList.getClientlist().contains(dataPacket.getToken()))){
            return MessageEnum.TOKEN_ERROR;
        }
        this.refillMap(allPlay);
        MessageEnum out1 = this.rechargeWeapons(allPlay,dataPacket);
        if(out1 == MessageEnum.AMMO_ERROR || out1 == MessageEnum.TOO_MUCH_POWERUPS)
            return out1;
        this.transferMarks(allPlay);
        this.scoreCounter(allPlay,dataPacket);
        allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).setEndturn(true);
        return MessageEnum.OK;
    }


    /**
     * refills the empty ammo and weapons slots of the map
     * @param i class containing information about the current match
     */
    public void refillMap( InitializeAllPlay i) throws RemoteException {
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
     * transfer marks to players
     */
    private void transferMarks(InitializeAllPlay i) {
        i.getCurrentPlayerState().forEach((player, cps) -> {
        cps.getControlMarks().forEach(((player1, integer) -> {
            cps.getBoard().getMarksBox().setMyMarksMap(player1,integer);
        }));
        });
    }
    /**
     * recharges weapons selected by the player, returns error if the player
     * hasn't enough ammo or gives more payingpowerups than the needed ones
     * @param i class containing information about the current match
     * @param d class containing necessary parameters sent by client
     * @return a message saying if the required action was successful or not
     */
    private MessageEnum rechargeWeapons(InitializeAllPlay i, DataPacket d) throws RemoteException {
        HashMap<Munitions, Integer> cost = new HashMap<>();
        cost.put(Munitions.RED, 0);
        cost.put(Munitions.YELLOW, 0);
        cost.put(Munitions.BLUE, 0);
        for(Weapon w : d.getWeaponsToBeRecharged()){
            for(Weapon w2 : i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getWeaponsList()) {
                if(w.getName()==w2.getName()) {
                    if (w.getFirstPrice().containsKey(Munitions.RED))
                        cost.put(Munitions.RED, cost.get(Munitions.RED) + w.getFirstPrice().get(Munitions.RED));
                    if (w.getFirstPrice().containsKey(Munitions.YELLOW))
                        cost.put(Munitions.YELLOW, cost.get(Munitions.YELLOW) + w.getFirstPrice().get(Munitions.YELLOW));
                    if (w.getFirstPrice().containsKey(Munitions.BLUE))
                        cost.put(Munitions.BLUE, cost.get(Munitions.BLUE) + w.getFirstPrice().get(Munitions.BLUE));
                }
            }
        }
        for(PowerUp pw : d.getPaymentPowerUp()){
            for(PowerUp pw2 : i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getPowerupList()) {
                if(pw.getId()==pw2.getId() && pw.getColor()==pw2.getColor()) {
                    cost.put(Munitions.RED, cost.get(Munitions.RED) - pw.munitionsChecker(Munitions.RED));
                    cost.put(Munitions.YELLOW, cost.get(Munitions.YELLOW) - pw.munitionsChecker(Munitions.YELLOW));
                    cost.put(Munitions.BLUE, cost.get(Munitions.BLUE) - pw.munitionsChecker(Munitions.BLUE));
                }
            }
        }
        if(cost.get(Munitions.RED)<0 || cost.get(Munitions.YELLOW)<0 || cost.get(Munitions.BLUE)<0)
            return MessageEnum.TOO_MUCH_POWERUPS;
        if(i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED)-cost.get(Munitions.RED)<0 || i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW)-cost.get(Munitions.YELLOW)<0 || i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE)-cost.get(Munitions.BLUE)<0)
            return MessageEnum.AMMO_ERROR;
        else{
            ArrayList<PowerUp> toRemove = new ArrayList<>();
            for(PowerUp pw : d.getPaymentPowerUp()) {
                for(PowerUp pw2 : i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getPowerupList()) {
                    if(pw.getId()==pw2.getId() && pw.getColor()==pw2.getColor())
                        toRemove.add(pw2);
                }
            }
            i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getPowerupList().removeAll(toRemove);
            i.getCurrentDeckState().getPowerupdeck().addAll(toRemove);
            Collections.shuffle(i.getCurrentDeckState().getPowerupdeck());
            for(Weapon w : d.getWeaponsToBeRecharged()) {
                for(Weapon w2 : i.getCurrentPlayerState().get(d.getPlayer()).getBoard().getWeaponsList()) {
                    if(w.getName()==w2.getName())
                        w2.setLoaded(true);
                }
            }
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
    private void scoreCounter(InitializeAllPlay i, DataPacket dataPacket){
        HashMap<Player,Integer> score = new HashMap<>();
        HashMap<Player,Integer> deathcounter = new HashMap<>();
        i.getCurrentPlayerState().forEach(((player, currentPlayerState) -> {
            score.put(player,0);
            deathcounter.put(player,0);
            if(i.getStateSelectedMode().getSelectedmode()== Mode.DOMINATION && currentPlayerState.getPlayerposition().getCurrentcell().getSpawnpointzone()!=null){
                currentPlayerState.getBoard().getDamageBox().increaseDamage(1,player);
                if(currentPlayerState.getPlayerposition().getCurrentcell().getInCellPlayer().size()==1)
                    currentPlayerState.getPlayerposition().getCurrentcell().getSpawnpointzone().getSPDamage().add(player);
            }
        }));
        i.getCurrentPlayerState().forEach(((player, currentPlayerState) -> {
            if(currentPlayerState.getBoard().getDamageBox().getDamage()[10]!=null) {
                currentPlayerState.getPlayerposition().setCurrentroom(null);
                currentPlayerState.getPlayerposition().setCurrentcell(null);
                DamageBox db = currentPlayerState.getBoard().getDamageBox();
                score.put(db.getDamage()[0],score.get(db.getDamage()[0])+1);
                Player[] points=this.damageScoreBoard(db.getDamage());
                for(int j=0; j<points.length;j++){
                    if(points[j]!=null && points[j]!=player)
                        score.put(points[j],score.get(points[j])+db.getMaxPointArray()[db.getMaxPointIndex()+j]); }
                db.setMaxPointIndex(db.getMaxPointIndex()+1);
                deathcounter.put(db.getDamage()[10],deathcounter.get(db.getDamage()[10])+1);
                int z=0;
                while(i.getSkullArray()[z]!=null)
                    z++;
                i.getSkullArray()[z]=db.getDamage()[10];
                i.getSecondSkullArray()[z]=db.getDamage()[11];
                if(db.getDamage()[11]!=null)
                    i.getCurrentPlayerState().get(db.getDamage()[11]).getBoard().getMarksBox().setMyMarksMap(player,1);
                for(int j=0;j<12;j++)
                    db.getDamage()[j]=null; }
        }));
        deathcounter.forEach((player, integer) -> {
            if(integer>1)
                score.put(player,score.get(player)+1);
        });
        score.forEach(((player, integer) ->
            i.getChartScore().setScore(player,integer)
        ));
        short k=0;
        for(Room r : i.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell c : r.getCellsList()){
                if(c.getSpawnpointzone()!=null){
                    if(c.getSpawnpointzone().getSPDamage().size()>=8)
                        k++;
                }
            }
        }
        if(dataPacket.getPlayer()==i.getLastTurnPlayer() && i.getStateSelectedMode().getSelectedmode()==Mode.DOMINATION && i.isFinalfrenzy()){
            for(Room r : i.getStateSelectedMap().getSelectedmap().getRoomList()){
                for(Cell c : r.getCellsList()){
                    if(c.getSpawnpointzone()!=null){
                        if(!c.getSpawnpointzone().getSPDamage().isEmpty()){
                        HashMap<Player,Integer> spawnscore = new HashMap<>();
                        for(int z=0;z<c.getSpawnpointzone().getSPDamage().size();z++) {
                            spawnscore.putIfAbsent(c.getSpawnpointzone().getSPDamage().get(z), 0);
                            spawnscore.put(c.getSpawnpointzone().getSPDamage().get(z), spawnscore.get(c.getSpawnpointzone().getSPDamage().get(z)) + 1); }
                        ArrayList<Player> order1 = new ArrayList<>();
                        order1.addAll(spawnscore.keySet());
                        for(int j=0;j<order1.size();j++){
                            for(int y=1;y<order1.size()-j;y++){
                                if(spawnscore.get(order1.get(y-1))<spawnscore.get(order1.get(y))){
                                    Player temp = order1.get(y);
                                    order1.set(y,order1.get(y-1));
                                    order1.set(y-1,temp);
                                }
                            } }
                        int inc=0;
                        i.getChartScore().setScore(order1.get(inc),c.getSpawnpointzone().getPointArray()[inc]);
                        for (int l=1;l<order1.size();l++) {
                            if (spawnscore.get(order1.get(l)) == spawnscore.get(order1.get(l - 1))) {
                                i.getChartScore().setScore(order1.get(l), c.getSpawnpointzone().getPointArray()[inc]); } else {
                                inc = l;
                                i.getChartScore().setScore(order1.get(l), c.getSpawnpointzone().getPointArray()[inc]); }
                        }
                        }
                    }
                }
            } }
        if(i.getStateSelectedMode().getSelectedmode()==Mode.DOMINATION && (k>=2 || i.getSkullArray()[7]!=null))
            i.setFinalfrenzy(true);
        //if(i.getSkullArray()[7]!=null && i.getStateSelectedMode().getSelectedmode()==Mode.BASE) //
        // todo stato frenesia finale
    }


    /**
     * returns the sorted turn score chart counting damage done by all players to the dead ones
     * @param p array containing players'damages
     * @return a sorted array
     */
    private Player[] damageScoreBoard(Player[] p){
        HashMap<Player,Integer> dc = new HashMap<>();
        ArrayList<Player> order = new ArrayList<>();
        for(int j=0;j<12;j++){
            if(p[j]!=null) {
                if (!dc.containsKey(p[j])) {
                    dc.put(p[j], 1);
                    order.add(p[j]); }
                else
                    dc.put(p[j], dc.get(p[j]) + 1); } }
        int k = dc.size();
        Player[] result = new Player[k];
        int m=0;
        for(Player pp : order){
            result[m]=pp;
            m++;
        }
        for(int j=0;j<k;j++){
            for(int y=1;y<k-j;y++){
              if(dc.get(result[y-1])<dc.get(result[y])){
                  Player temp = result[y];
                  result[y] = result[y-1];
                  result[y-1]=temp; }
            }
        }
        return result;
    }}
