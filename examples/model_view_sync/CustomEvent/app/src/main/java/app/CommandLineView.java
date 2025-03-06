package app;
/**
 * Example of the view registering a handler.
 * To execute:
 *     java app/src/main/java/app/CommandLineView.java 
 * @author S. Gothard * date:
 * 3/1/2023
 */
class CommandLineView {

    public static void main(String[] args) {
        Model model = new Model();
        model.setOnGotTen(i -> System.out.println(i + ": Rolled a 10!"));
        System.out.println("Starting Game:");
        model.playGame();

    }
}

