package Model;

import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Model.Observer e){
        observers.add(e);
    }

    public void removeObserver(Model.Observer e){
        observers.remove(e);
    }

    public void notifyObserver(){
    }

    public ArrayList<Observer> getObservers(){
        return this.observers;
    }
}