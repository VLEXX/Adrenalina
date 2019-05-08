package Model;

import Model.InitializeAllPlay;


public class WaitingState implements State {

    @Override
    public int doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        return 0;
    }
}
