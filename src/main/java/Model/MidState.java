package Model;

public class MidState implements State {

    @Override
    public Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        return new Message("ok");
    }
}
