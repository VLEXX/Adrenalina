package model.modelstates;

import model.datapacket.DataPacket;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;

public class EndTurnState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        //TODO
        return MessageEnum.OK;
    }
}
