package app;

public class Model {
    private Observer observer;
    public void setObserver(Observer obs) {
        observer = obs;
    }
    public void playGame() {
        for (int i = 0; i < 500; ++i) {
            if ((int) (Math.random() * 100) == 10 && observer != null) {
                observer.gotTen(i);
            }
        }
        if (observer != null) {
            observer.finished(500);
        }
    }
}
