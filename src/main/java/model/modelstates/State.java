/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

public interface State {
    MessageEnum doAction(DataPacket dataPacket);
}
