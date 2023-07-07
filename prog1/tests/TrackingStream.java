import java.io.PrintStream;

/**
 * Overrides PrintStream in order to track all printing for testing purposes.
 *     Can be used instead of System.out. Results still print to screen
 *     and are also logged.
 * @author Sarah Gothard, 2023  
 */
class TrackingStream extends PrintStream {
    public String printed = "";

    public TrackingStream() {
        super(System.out);
    }

    @Override
    public void print(Object obj) {
        super.print(obj);
        printed += obj;
    }   

    @Override
    public void println(Object obj) {
        super.println(obj);
        printed += obj + System.lineSeparator();
    }   
}