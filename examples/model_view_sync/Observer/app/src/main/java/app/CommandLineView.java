package app;

/**
 * Example of the view registering a handler. To execute: 
 *    java app/src/main/java/app/CommandLineView.java
 *
 * @author S. Gothard date: 3/5/2025
 */
class CommandLineView implements Observer {

    public static void main(String[] args) {
        CommandLineView view = new CommandLineView();
        view.play();
    }

    private final Model model;

    public CommandLineView() {
        model = new Model();
    }

    public void play() {
        model.setObserver(this);
        model.playGame();
    }

    @Override
    public void gotTen(int i) {
        System.out.println(i + ": Rolled a 10!");
    }

    @Override
    public void finished(int i) {
        System.out.println("Completed " + i + " rolls!");
    }
}
