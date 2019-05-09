package Model;

import java.util.ArrayList;

public interface SubjectUpdate {
    public void addObserver(ObserverUpdate e);

    public void removeObserver(ObserverUpdate e);

    public void notifyObserver();

    public ArrayList<ObserverUpdate> getObservers();
}
