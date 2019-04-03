//Author: Alex Saletti
package Model;

public class Map {
    private String mapname;


    public Map() {                   //costruttore
        mapname = null;
    }

    public String getMapName() {   //restituisce il nome della mappa
        return mapname;
    }

    public void setMapName(String newname) {       //setta il nome della mappa
        mapname = newname;
    }

}




