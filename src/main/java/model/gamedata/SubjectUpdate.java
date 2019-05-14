/**
 * @author Federico Scatà
 */
package model.gamedata;

import servercontroller.ObserverUpdate;

import java.util.ArrayList;

public interface SubjectUpdate {
    void addObserver(ObserverUpdate e);

    void removeObserver(ObserverUpdate e);

    void notifyObserver();

    ArrayList<ObserverUpdate> getObservers();
}
