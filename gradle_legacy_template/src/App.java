/*
 * This class contains the main method of the application
 */


public class App {

    public static void main(String[] args) {
        System.out.println(addArgs(args));
    }

    public static int addArgs(String []args) {
        int sum = 0;
        
        if (args.length > 0) {
            for (var item : args) {
                sum += Integer.parseInt(item);
            }
        }
        return sum;
        
    }
}
