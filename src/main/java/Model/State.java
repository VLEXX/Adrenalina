/**
 * @author Federico Scatà
 */
package Model;

public interface State {
    MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay);
}
