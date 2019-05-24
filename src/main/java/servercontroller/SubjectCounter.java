package servercontroller;

import java.util.ArrayList;

public interface SubjectCounter {

    void addObserver(ObserverCounter e);

    void removeObserver(ObserverCounter e);

    void notifyObserver();

    ArrayList<ObserverCounter> getObservers();
}
