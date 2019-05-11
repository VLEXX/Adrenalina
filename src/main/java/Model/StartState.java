/**
 * @author Federico Scatà
 */
package Model;

public class StartState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        if (dataPacket.getStatesEnum().equals(StatesEnum.MOVE)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new MoveState());
            allPlay.notifyObserver();
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.PICK_UP)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new PickUpState());
            allPlay.notifyObserver();
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.SHOOT)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new ShootState());
            allPlay.notifyObserver();
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.END)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new EndTurnState());
            allPlay.notifyObserver();
            return MessageEnum.OK;
        } else {
            return MessageEnum.ACTION_ERROR;
        }
    }
}