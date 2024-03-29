import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class AppTest {
    /**
     * Example test of Hello World. Delete as needed.
     */
    @Test
    public void test_App_HelloWorld(){
        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertEquals("Hello, world!", line);
            br.close();
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_App_WithDependencyInjection_noArgs() {
        TrackingStream trackingOut = new TrackingStream();
        System.setOut(trackingOut);

        String []args = {};
        App.main(args);
        assertEquals("Hello, world!" + System.lineSeparator(), 
                    trackingOut.printed);
    }
}
