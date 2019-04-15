package ServerController;

import Model.Action;

public class StartState implements State{

    @Override
    public void doAction(ContextState contextState) {
        //TODO
        contextState.setState(this);
        //1 - Invia al client richiesta di input (RACCOGLI|SPARA|MUOVITI)
        //2 - Ricevi dal client nuova azione (RACCOGLI|SPARA|MUOVITI)
        //3 - A seconda dell'azione ricevuta in input cambia stato
    }
}
