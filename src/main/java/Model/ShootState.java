package Model;

public class ShootState implements State {

    @Override
    public Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        //TODO
        return new Message("ok");
    }
}
