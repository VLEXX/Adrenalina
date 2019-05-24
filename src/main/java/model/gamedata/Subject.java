/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.Observer;

import java.util.ArrayList;

public interface Subject {

    void addObserver(Observer e);

    void removeObserver(Observer e);

    void notifyObserver();

    ArrayList<Observer> getObservers();
}