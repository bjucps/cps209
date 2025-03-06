package app;

public class Model {

    private TenEvent tenEvent;

    public void setOnGotTen(TenEvent t) {
        tenEvent = t;
    }

    public void playGame() {
        for (int i = 0; i < 500; ++i) {
            if ((int) (Math.random() * 100) == 10 && tenEvent != null) {
                tenEvent.gotTen(i);
            }
        }
    }
}
