package app;

public class CommandPrompt implements Observer {

    @Override
    public void hasMoved(int moveValue) {
        System.out.println("Move was " +moveValue);
    }

    @Override
    public void gameOver() {
        System.out.println("GAMEOVER");
    }
    
}
