//Author: Federico Scatà
package Model;

//Classe che memorizza la mappa corrente selezionata del gioco
public class StateSelectedMap {
    private Map SelectedMap;    //mappa selezionata

    //Costruttore che inizializza la mappa selezionata a "null"
    public StateSelectedMap(){
        SelectedMap=null;
    }

    //Ritorna la mappa selezionata
    public Map getSelectedMap() {
        return SelectedMap;
    }

    //Setta la mappa selezionata
    public void setSelectedMap(Map selectedMap) {
        SelectedMap = selectedMap;
    }
}

