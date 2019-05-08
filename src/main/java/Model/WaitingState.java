package Model;

public class WaitingState implements State {

    @Override
    public int doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        while(true){
            if(allPlay.getPlayerState(dataPacket.getPlayer())!=this){
                break;
            }
        }
        return 0;
    }
}
