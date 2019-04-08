package Model;

import java.util.ArrayList;

public interface Subject {

    public void addObserver(Model.Observer e);

    public void removeObserver(Model.Observer e);

    public void notifyObserver();

    public ArrayList<Observer> getObservers();
}