//Author: Alex Saletti
package Model;

import java.util.ArrayList;

public class Map {
    private String mapname;//nome della mappa
    private ArrayList<Room> roomlist;


    public Map() {                   //costruttore
        mapname = null;
        roomlist = new ArrayList<>();
    }

    public String getMapname() {   //restituisce il nome della mappa
        return mapname;
    }       //restituisce il nome della mappa

    public void setMapname(String newname) {       //setta il nome della mappa
        mapname = newname;
    }       //permette di impostare il nome della mappa

    public ArrayList<Room> getRoomList() {      //restituisce la lista delle stanze
        return roomlist;
    }

    public void addRoom(Room r) {        //aggiunge una stanza
        roomlist.add(r);
    }
}




