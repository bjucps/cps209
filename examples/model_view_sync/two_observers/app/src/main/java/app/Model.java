package app;

import java.util.ArrayList;

public class Model {
    private static Model instance;
    private int moveValue;
    private final ArrayList<Observer> observers;
    private Model() {
        observers = new ArrayList<>();
        moveValue = 0;
    }
    public static Model getInstance () {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }


    public void addObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    public void generateMove() {
        moveValue = (int) (Math.random()*20);
        observers.stream().forEach(o -> o.hasMoved(moveValue));
        
        if (moveValue == 10) {
            observers.stream().forEach(o -> o.gameOver());
        }
    }
}
