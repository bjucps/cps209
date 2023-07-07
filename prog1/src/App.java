import java.io.PrintStream;

/**
 * Starting app program. Uses a variable to store the output object.
 *     out variable can be assigned to other objects for testing.
 */
public class App {
    public static PrintStream out = System.out;

    public static void print(Object obj) {
        out.print(obj);
    } 

    public static void println(Object obj) {
        out.println(obj);
    } 
        
    public static void main(String[] args) {
        println("Hello, world!");
    }
}
