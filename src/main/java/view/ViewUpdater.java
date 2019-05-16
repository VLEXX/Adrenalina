package view;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.playerdata.Player;
import view.viewstates.ViewState;
import java.util.HashMap;

public class ViewUpdater {


    public void updateView(UpdatePacket updatePacket, ViewDatabase viewDatabase, HashMap<StatesEnum, ViewState> hashMap, Player player){
        viewDatabase.getViewCurrentPlayerState().setCurrentPlayerState(updatePacket.getCurrentPlayerState());
        viewDatabase.getViewMapState().setSelectedMap(updatePacket.getMap());
        viewDatabase.getViewChartScore().setChartScore(updatePacket.getChart());
        if(updatePacket.getStatesEnum().equals(StatesEnum.ACTION)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.ACTION));
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.POWERUP)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.POWERUP));
        }
        viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
    }
}
