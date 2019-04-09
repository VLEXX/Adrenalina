//Author: Federico Scatà
package Model;

//enum che identifica i tipi di stati "azione" in cui può trovarsi il giocatore
public enum Action {
    PICK_UP,    //raccogli
    SHOOT,      //Spara
    MOVE,       //Spostati
    WAIT,       //Attendi
    START_TURN, //Inizio del turno
    END_TURN    //Fine del turno
}
