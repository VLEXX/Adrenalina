/**
 * @author Federico Scatà
 */
package Model;

public class StartState implements State {

    @Override
    public Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        if(dataPacket.getStatesEnum().equals(StatesEnum.MOVE)){
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new MoveState());
            allPlay.notifyObserver();
            return new Message("ok");
        }
        if(dataPacket.getStatesEnum().equals(StatesEnum.PICK_UP)){
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new PickUpState());
            allPlay.notifyObserver();
            return new Message("ok");
        }
        if(dataPacket.getStatesEnum().equals(StatesEnum.SHOOT)){
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new ShootState());
            allPlay.notifyObserver();
            return new Message("ok");
        }
        if(dataPacket.getStatesEnum().equals(StatesEnum.END)){
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), new EndTurnState());
            allPlay.notifyObserver();
            return new Message("ok");
        }
        else {
            return new Message("WRONG INPUT! Please, choose the right action...\n");
        }
    }
}