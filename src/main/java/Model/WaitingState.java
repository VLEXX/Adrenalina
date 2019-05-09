/**
 * @author Federico Scatà
 */
package Model;

public class WaitingState implements State {

    @Override
    public Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        while(true){
            if(allPlay.getPlayerState(dataPacket.getPlayer())!=this){
                break;
            }
        }
        return new Message("ok");
    }
}
