/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.map.*;

import java.io.Serializable;

//Classe che memorizza la mappa corrente selezionata del gioco
public class StateSelectedMap implements Serializable {
    private Map selectedmap;    //mappa selezionata
    private StrategyMap strategyMap;

    //Costruttore che inizializza la mappa selezionata a "null"
    public StateSelectedMap() {
        selectedmap = null;
        strategyMap = null;
    }

    //Ritorna la mappa selezionata
    public Map getSelectedmap() {
        return selectedmap;
    }

    //Setta la mappa selezionata
    public void setSelectedmap() {
        this.selectedmap = strategyMap.initializeMap();
    }

    //Setta la StrategyMap per l'inizializzazione della mappa
    public void setStrategyMap(int i) {
        if (i == 0) {
            strategyMap = new InitializeMap1();
        }
        if (i == 1) {
            strategyMap = new InitializeMap2();
        }
        if (i == 2) {
            strategyMap = new InitializeMap3();
        }
        if (i == 3) {
            strategyMap = new InitializeMap4();
        }
    }
}

