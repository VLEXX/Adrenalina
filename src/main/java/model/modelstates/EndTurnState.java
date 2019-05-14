package model.modelstates;

import model.datapacket.DataPacket;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.map.*;
import model.munitions.Munitions;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.util.HashMap;

public class EndTurnState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay i) {
        //TODO
        return MessageEnum.OK;
    }

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

    private int rechargeWeapons(InitializeAllPlay i, DataPacket d){
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
        cost.forEach();
i
    }
}
