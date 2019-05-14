/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;

public class WaitingState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        while (true) {
            if (allPlay.getPlayerState(dataPacket.getPlayer()) != this) {
                break;
            }
        }
        return MessageEnum.OK;
    }
}
