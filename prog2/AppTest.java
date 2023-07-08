import java.io.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    /**
     * Runs the program like the user would.
     */
    @Test
    public void test_80_main_normal()    {
        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar", "src/ice.png").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertEquals("Metadata in src/ice.png:", line);
            line = br.readLine();
            assertTrue(line.startsWith("Software: "));
            line = br.readLine();
            assertEquals("Signature: 3248a69c033c15e46356a9ecb996c652", line);
            line = br.readLine();
            assertEquals("Delay: 42", line);
            br.close();
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_80_main_sample_png()    {
        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar", "src/sample.png").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertEquals("Metadata in src/sample.png:", line);
            line = br.readLine();
            assertTrue(line.startsWith("Software: "));
            br.close();
                        
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_80_main_no_args()    {
        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertTrue(line==null || line.equals("Usage: Java App [filename] or arun [filename]"));
            br.close();

            if (line == null) {
                is = process.getErrorStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                line = br.readLine();
                assertEquals("Usage: Java App [filename] or arun [filename]", line);
                br.close();
            }
                        
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_80_method_too_long() {
        String []folders = {"src", "tests"};
        for (String folder: folders) {
            File f = new File(folder);
            LongMethodChecker.checkFiles(f.listFiles());
        }
    }

    @Test
    public void test_a105_modify_metadata() {

        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar", "src/sample.png", "Software", "BJU-CpS-209").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertEquals("Metadata in src/sample.png:", line);
            line = br.readLine();
            assertEquals("Software: BJU-CpS-209", line);
            br.close();
                        
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_a105_modify_metadata_fail() {

        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar", "src/sample.png", "Denomination", "Presbyterian").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertTrue(line==null || line.equals("No Denomination found"));
            br.close();

            if (line == null) {
                is = process.getErrorStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                line = br.readLine();
                assertEquals("No Denomination found", line);
                br.close();
            }
                        
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }
}
