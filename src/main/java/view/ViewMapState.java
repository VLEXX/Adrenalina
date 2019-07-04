/**
 * @author Federico Scatà
 */
package view;

import model.map.Map;

/**
 * Class that contains selected map of the game
 */
public class ViewMapState {
    private Map currentmap; //memorizza lo stato della mappa corrente

    //costruttore
    public ViewMapState(){
        this.currentmap = null;
    }

    //ritorna lo stato della mappa
    public Map getSelectedMap() {
        return this.currentmap;
    }

    //setta lo stato della mappa
    public void setSelectedMap(Map selectedMap) {
        this.currentmap = selectedMap;
    }
}
