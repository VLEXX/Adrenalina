package view;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.playerdata.Player;
import view.viewstates.ViewState;
import java.util.HashMap;

public class ViewUpdater {


    public void updateView(UpdatePacket updatePacket, ViewDatabase viewDatabase, HashMap<StatesEnum, ViewState> hashMap, Player player){
        if(updatePacket!=null)
        viewDatabase.getViewCurrentPlayerState().setCurrentPlayerState(updatePacket.getCurrentPlayerState());
        viewDatabase.getViewMapState().setSelectedMap(updatePacket.getMap());
        viewDatabase.getViewChartScore().setChartScore(updatePacket.getChart());
        if(updatePacket.getStatesEnum()==null){
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
            return;
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.ACTION)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.ACTION));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.POWERUP)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.POWERUP));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SPAWN)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SPAWN));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SHOOT)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SHOOT));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SHOOT_SECOND)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SHOOT_SECOND));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SHOOT_THIRD)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SHOOT_THIRD));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.PICK_UP)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.PICK_UP));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.MOVE)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.MOVE));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.END)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.END));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.WAIT)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.WAIT));
            viewDatabase.setViewPlayerPosition(updatePacket.getPositionHashMap());
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
    }
}
