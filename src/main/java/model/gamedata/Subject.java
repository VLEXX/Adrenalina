/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.Observer;

import java.util.ArrayList;

public interface Subject {

    public void addObserver(Observer e);

    public void removeObserver(Observer e);

    public void notifyObserver();

    public ArrayList<Observer> getObservers();
}