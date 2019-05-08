package Model;

public class StartState implements State {

    @Override
    public Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        //TODO
        //1 - Invia al client richiesta di input (RACCOGLI|SPARA|MUOVITI)
        //2 - Ricevi dal client nuova azione (RACCOGLI|SPARA|MUOVITI)
        //3 - A seconda dell'azione ricevuta in input cambia stato
        return new Message("ok");
    }
}