import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;

/**
 * Tests App.java. There can be separate test prgorams for each Java file.
 * @author Sarah Gothard, 2023
 */
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
            assertTrue("Was " + line, line.startsWith("Software: "));
            line = br.readLine();
            assertEquals("Signature: 3248a69c033c15e46356a9ecb996c652", line);
            line = br.readLine();
            assertEquals("Delay: 42", line);
            br.close();
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    /**
     * Runs the program like the user would.
     */
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
            assertTrue("Was " + line, line.startsWith("Software: "));
            br.close();
                        
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    /**
     * Runs the program like the user would.
     */
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

    /**
     * Helper method for checking file length. A bit long.
     * @param filename the file to check.
     */
    private void checkLength(File f) {
        try (BufferedReader file = new BufferedReader(new FileReader(f))) {
            int level = 0, numLines = 0, lineNumber = 1;

            String line = file.readLine();

            while (line != null) {
                int countOpen = line.length() - line.replace("{", "").length();
                int countClose = line.length() - line.replace("}", "").length();
                level += countOpen - countClose;
                if (level > 1) {
                    ++numLines;
                } else if (countClose > 0 && level == 1) {
                    if (numLines > 30) {
                        fail("Method ending at " + lineNumber + " in " +  
                                f.getName() + " is longer than 30 lines.");
                    }
                    numLines = 0;
                } else if (countOpen > 0 && level == 2) {
                    numLines = 0;
                }
                line = file.readLine();
                ++lineNumber;
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    /**
     * Recursively checks the length of methods in all java files.
     * @param files list of files to check
     */
    private void checkFiles(File [] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                checkFiles(file.listFiles());
            } else if (file.getName().endsWith(".java")) {
                checkLength(file);
            }
        }
    }

    /**
     * Check method lengths for all files in the src folder. 
     */
    @Test
    public void test_80_method_too_long() {

        // Gets all the files in the src folder
        File f = new File("src");

        // recursively checks all the java files
        checkFiles(f.listFiles());

    }

    /**
     * Check method lengths for all files in the src folder. 
     */
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

    /**
     * Check method lengths for all files in the src folder. 
     */
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
