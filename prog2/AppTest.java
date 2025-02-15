package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;


public class AppTest {

    /**
     * Testing with TrackingStream
     */
    @Test
    public void test_80_main_normal() {
        TrackingStream outStream = new TrackingStream();
        System.setOut(outStream);
        System.setErr(outStream);
        String [] args =  {"ice.png"};
        App.main(args);
        assertTrue(outStream.printed.contains("Metadata in ice.png:"));
        assertTrue(outStream.printed.contains("Software: "));
        assertTrue(outStream.printed.contains("Signature: 3248a69c033c15e46356a9ecb996c652"));
        assertTrue(outStream.printed.contains("Delay: 42"));
    }

    @Test
    public void test_80_main_sample_png() {
        TrackingStream outStream = new TrackingStream();
        System.setOut(outStream);
        System.setErr(outStream);
        String [] args = {"sample.png"};
        App.main(args);
        assertTrue(outStream.printed.contains("Metadata in sample.png:"));
        assertTrue(outStream.printed.contains("Software: "));
    }

    @Test
    public void test_80_main_no_args() {
        TrackingStream outStream = new TrackingStream();
        System.setOut(outStream);
        System.setErr(outStream);
        String [] args = {};
        App.main(args);
        assertTrue(outStream.printed.contains("Usage: Java App [filename] or arun [filename]"));
    }

    /**
     * LongMethodChecker is available in the prog1 folder.
     */
    @Test
    public void test_80_method_too_long() {
        File f = new File(System.getProperty("user.dir"));
        LongMethodChecker.checkFiles(f.listFiles());
    }

    @Test
    public void test_a105_modify_metadata() {
        TrackingStream outStream = new TrackingStream();
        System.setOut(outStream);
        System.setErr(outStream);
        String [] args = {"sample.png", "Software", "BJU-CpS-209"};
        App.main(args);
        assertTrue(outStream.printed.contains("Metadata in sample.png:"));
        assertTrue(outStream.printed.contains("Software: BJU-CpS-209"));
    }

    @Test
    public void test_a105_modify_metadata_fail() {
        TrackingStream outStream = new TrackingStream();
        System.setOut(outStream);
        System.setErr(outStream);
        String [] args = {"sample.png", "Denomination", "Presbyterian"};
        App.main(args);
        assertTrue(outStream.printed == null || outStream.printed.equals("") || outStream.printed.startsWith("No Denomination found"));
    }



/* Testing with Process Builder. 
To use tests with ProcessBuilder, please add the following lines to the bottom of build.gradle:

jar {
  manifest {
    attributes(
      'Main-Class': 'app.App'
    )
  }
}

*/
    @Test
    public void test_80_main_normal_processBuilder() {
        System.out.println("User dir: " + System.getProperty("user.dir"));
        try {
            Process process = new ProcessBuilder("java",
                    "-jar", "build/libs/app.jar", "ice.png").start();
            try (InputStream is = process.getInputStream(); InputStreamReader isr = new InputStreamReader(is); BufferedReader br = new BufferedReader(isr)) {
                String line = br.readLine();
                assertEquals("Metadata in ice.png:", line);
                line = br.readLine();
                assertTrue(line.startsWith("Software: "));
                line = br.readLine();
                assertEquals("Signature: 3248a69c033c15e46356a9ecb996c652", line);
                line = br.readLine();
                assertEquals("Delay: 42", line);
                br.close();
            }
        } catch (IOException e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_80_main_sample_png_processBuilder() {
        try {
            Process process = new ProcessBuilder("java",
                    "-jar", "build/libs/app.jar", "sample.png").start();
            try (InputStream is = process.getInputStream(); InputStreamReader isr = new InputStreamReader(is); BufferedReader br = new BufferedReader(isr)) {

                String line = br.readLine();
                assertEquals("Metadata in sample.png:", line);
                line = br.readLine();
                assertTrue(line.startsWith("Software: "));

            }
        } catch (IOException e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void test_80_main_no_args_processBuilder() {
        String line;
        try {
            Process process = new ProcessBuilder("java",
                    "-jar", "build/libs/app.jar").start();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                line = br.readLine();
                // Check standard out
                assertTrue(line == null
                        || line.equals("Usage: Java App [filename] or arun [filename]"));

            }

            // Check standard error if standard out was null
            if (line == null) {
                try (BufferedReader brErr = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    line = brErr.readLine();
                    assertEquals("Usage: Java App [filename] or arun [filename]", line);
                }
            }

        } catch (IOException ex) {
            fail("Exception: " + ex);
        }
    }

    /**
     * LongMethodChecker is available in the prog1 folder.
     */
    @Test
    public void test_80_method_too_long_processBuilder() {
        File f = new File(System.getProperty("user.dir"));
        LongMethodChecker.checkFiles(f.listFiles());
    }

    @Test
    public void test_a105_modify_metadata_processBuilder() {
        try {
            Process process = new ProcessBuilder("java",
                    "-jar", "build/libs/app.jar", "sample.png", "Software", "BJU-CpS-209").start();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = br.readLine();
                assertEquals("Metadata in sample.png:", line);
                line = br.readLine();
                assertEquals("Software: BJU-CpS-209", line);

            }

        } catch (IOException ex) {
            fail("Exception: " + ex);
        }
    }

    @Test
    public void test_a105_modify_metadata_fail_processBuilder() {

        try {
            String line = null;
            Process process = new ProcessBuilder("java",
                    "-jar", "build/libs/app.jar", "sample.png", "Denomination", "Presbyterian").start();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                line = br.readLine();
                assertTrue(line == null || line.equals("No Denomination found"));
            }

            // Check standard error if standard out was null
            if (line == null) {
                try (BufferedReader brErr = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    line = brErr.readLine();
                    assertEquals("No Denomination found", line);
                }
            }
        } catch (IOException e) {
            fail("Exception: " + e);
        }
    }
}
