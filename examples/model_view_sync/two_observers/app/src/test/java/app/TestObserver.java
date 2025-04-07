package app;

public class TestObserver implements Observer {

    private int hasMovedCount = 0;
    private int lastMove = -1;
    private int gameOverCount = 0;

    @Override
    public void hasMoved(int moveValue) {
        ++hasMovedCount;
        lastMove = moveValue;
    }

    @Override
    public void gameOver() {
        ++gameOverCount;
    }

    public int getHasMovedCount() {
        return hasMovedCount;
    }

    public int getLastMove() {
        return lastMove;
    }

    public int getGameOverCount() {
        return gameOverCount;
    }
    
}
