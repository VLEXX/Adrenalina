package Model;

public interface State {
    Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay);
}
