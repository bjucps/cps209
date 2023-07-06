import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

/**
 * Tests for Program 1. Please add tests for all methods created.
 * @author Sarah Gothard and ________________________
 */
public class AppAndStarshipTest {
    /**
     * Tests Starship default constructor: assigns default values to instance
     *     variables and checks them using getters.
     */
    @Test
    public void test_80_Starship_defaultConstructor() {
        Starship ship = new Starship();
        assertEquals(1.0, ship.getWarp(), .001);
        assertFalse(ship.isCloak());
        assertEquals(0, ship.getShields());
        if (ship.getCrew().get(0).equals("spock")) {
            assertEquals("kirk", ship.getCrew().get(1));
        } else if (ship.getCrew().get(1).equals("spock")) {
            assertEquals("kirk", ship.getCrew().get(0));
        } else {
            fail("Incorrect default crew: " + ship.getCrew());
        }
    }

    @Test
    public void test_80_Starship_setters() {
        Starship ship = new Starship();
        ship.setWarp(5.0);
        assertEquals(5.0, ship.getWarp(), .001);
        ship.setCloak(true);
        assertTrue(ship.isCloak());
        ship.setShields(6);
        assertEquals(6, ship.getShields());
        ship.setCrew("mccoy,uhura");
        assertEquals("mccoy", ship.getCrew().get(0));
        assertEquals("uhura", ship.getCrew().get(1));
    }

    @Test
    public void test_80_Starship_factory_valid_input() {
        String []args = {"--warp", "5.2", "--cloak", "--shields", "7", "--crew", "kirk,uhura,mccoy"};
        Starship ship = Starship.fromArgs(args);
        assertEquals(true, ship.isCloak());
        assertEquals(5.2, ship.getWarp(), .001);
        assertEquals("kirk", ship.getCrew().get(0));
        assertTrue(ship.getCrew().get(1).equals("mccoy") || ship.getCrew().get(1).equals("uhura"));
        assertTrue(ship.getCrew().get(2).equals("mccoy") || ship.getCrew().get(2).equals("uhura"));
        assertEquals(7, ship.getShields());
    }

    @Test
    public void test_80_Starship_factory_valid_input_reversed() {
        String []args = {"--crew", "kirk,uhura,mccoy", "--shields", "7", "--cloak", "--warp", "5.2"};
        Starship ship = Starship.fromArgs(args);
        assertEquals(true, ship.isCloak());
        assertEquals(5.2, ship.getWarp(), .001);
        assertEquals("kirk", ship.getCrew().get(0));
        assertTrue(ship.getCrew().get(1).equals("mccoy") || ship.getCrew().get(1).equals("uhura"));
        assertTrue(ship.getCrew().get(2).equals("mccoy") || ship.getCrew().get(2).equals("uhura"));
        assertEquals(7, ship.getShields());
    }

    @Test
    public void test_80_Starship_factory_no_input() {
        String []args = {};
        Starship ship = Starship.fromArgs(args);
        assertEquals(false, ship.isCloak());
        assertEquals(1, ship.getWarp(), .001);
        assertTrue(ship.getCrew().get(0).equals("kirk") || ship.getCrew().get(0).equals("spock"));
        assertTrue(ship.getCrew().get(1).equals("kirk") || ship.getCrew().get(1).equals("spock"));
        assertEquals(0, ship.getShields());
    }


    /**
     * Tests Starship fromArgs with incorrect argument: --wrap
     */
    @Test
    public void test_80_Starship_factory_invalid_input() {
        String []args = {"--warp", "5.2", "--cloak", "--wrap", "7", "--crew", "kirk,uhura,mccoy"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            // success
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }
    }

    /**
     * Tests App.main using example input. Uses TrackingStream in place of System.out.
     */
    @Test
    public void test_80_App_main() {
        TrackingStream out = new TrackingStream();
        App.out = out;
        String []args = {"--warp", "5.2", "--cloak", "--shields", "7", "--crew", "kirk,uhura,mccoy"};
        App.main(args);
        String expected = "Shields:\t[XXXXXXX--]" + System.lineSeparator() +
                "Warp:\t[5.2]" + System.lineSeparator() + "Cloak:\t[Engaged]" +
                System.lineSeparator() + "Bridge Crew:" + 
                System.lineSeparator() + "* kirk" + System.lineSeparator() +
                "* mccoy" + System.lineSeparator() + "* uhura" + 
                System.lineSeparator();
        assertEquals(expected, out.printed);
    }

    @Test
    public void test_80_App_main_fullshields() {
        TrackingStream out = new TrackingStream();
        App.out = out;
        String []args = {"--warp", "0", "--shields", "9", "--crew", "chekov,scotty,spock"};
        App.main(args);
        String expected = "Shields:\t[XXXXXXXXX]" + System.lineSeparator() +
                "Warp:\t[0.0]" + System.lineSeparator() + "Cloak:\t[Disengaged]" +
                System.lineSeparator() + "Bridge Crew:" + 
                System.lineSeparator() + "* chekov" + System.lineSeparator() +
                "* scotty" + System.lineSeparator() + "* spock" + 
                System.lineSeparator();
        assertEquals(expected, out.printed);
    }

    @Test
    public void test_80_App_main_noshields() {
        TrackingStream out = new TrackingStream();
        App.out = out;
        String []args = {"--warp", "15", "--crew", "spock"};
        App.main(args);
        String expected = "Shields:\t[---------]" + System.lineSeparator() +
                "Warp:\t[15.0]" + System.lineSeparator() + "Cloak:\t[Disengaged]" +
                System.lineSeparator() + "Bridge Crew:"  + System.lineSeparator() +
                 "* spock" + System.lineSeparator();
        assertEquals(expected, out.printed);
    }

    /**
     * Runs the program like the user would.
     */
    @Test
    public void test_80_run_process()    {
        try {
            Process process = new ProcessBuilder("java", 
                  "-jar", "build/libs/app.jar", "--cloak", "--warp", "3.14", 
                  "--shields", "1", "--crew", "kirk,mccoy,scotty").start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            assertEquals("Shields:\t[X--------]", line);
            line = br.readLine();
            assertEquals("Warp:\t[3.14]", line);
            line = br.readLine();
            assertEquals("Cloak:\t[Engaged]", line);
            line = br.readLine();
            assertEquals("Bridge Crew:", line);
            line = br.readLine();
            assertEquals("* kirk", line);
            line = br.readLine();
            assertEquals("* mccoy", line);
            line = br.readLine();
            assertEquals("* scotty", line);
            br.close();
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

    @Test
    public void test_80_method_too_long() {

        File f = new File("src");

        checkFiles(f.listFiles());

    }

    /**
     * Included to get full coverage from jacoco. App has an implicit default constructor.
     */
    @Test
    public void test_80_App_constructor() {
        App app = new App();
        assertNotNull(app);
    }

    /**
     * Tests Starship fromArgs with negative warp.
     */
    @Test
    public void test_90_Starship_factory_negative_warp() {
        String []args = {"--warp", "-.1"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Warp factor not a floating point number in the range 0-15"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }        
    }

    /**
     * Tests Starship fromArgs with warp that is too high.
     */
    @Test
    public void test_90_Starship_factory_too_high_warp() {
        String []args = {"--warp", "15.1"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Warp factor not a floating point number in the range 0-15"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }
    }

    /**
     * Tests Starship fromArgs with warp that is a String.
     */
    @Test
    public void test_90_Starship_factory_wrong_type_warp() {
        String []args = {"--warp", "hello"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Warp factor not a floating point number in the range 0-15"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }
    }
    
    /**
     * Tests Starship fromArgs with negative shield value.
     */
    @Test
    public void test_90_Starship_factory_negative_shields() {
        String []args = {"--shields", "-1"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Shields not an integer in the range 0-9"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }
        
    }

    /**
     * Tests Starship fromArgs with shields that are higher than valid range.
     */
    @Test
    public void test_90_Starship_factory_too_high_shields() {
        String []args = {"--shields", "10"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Shields not an integer in the range 0-9"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }        
    }

    /**
     * Tests Starship fromArgs with double shield value.
     */
    @Test
    public void test_90_Starship_factory_wrong_type_shields() {
        String []args = {"--shields", "4.5"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Shields not an integer in the range 0-9"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }        
    }

    /**
     * Tests Starship fromArgs with --shields with no value.
     */
    @Test
    public void test_90_Starship_factory_missing_value_shields() {
        String []args = {"--shields"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Last argument is a valid option, but has no configuration value"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }        
    }


    /**
     * Tests Starship fromArgs with --warp with no value.
     */
    @Test
    public void test_90_Starship_factory_missing_value_warp() {
        String []args = {"--warp"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Last argument is a valid option, but has no configuration value"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }        
    }

    /**
     * Tests Starship fromArgs with --crew with no value.
     */
    @Test
    public void test_90_Starship_factory_missing_value_crew() {
        String []args = {"--crew"};
        try {
            Starship.fromArgs(args);
            fail("Should have caused an illegal argument exception: " + args);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Last argument is a valid option, but has no configuration value"));
        } catch (Exception e) {
            fail("Wrong type of exception thrown: " + e);
        }        
    }


    /**
     * Tests Starship fromArgs for sorting crew members.
     */
    @Test
    public void test_a100_Starship_factory_sorted_crew() {
        String []args = {"--crew", "kirk,spock,mccoy,uhura,scotty,chekov"};
        String [] sorted = {"chekov", "kirk", "mccoy", "scotty", "spock", "uhura" };
        Starship starship = Starship.fromArgs(args);
        for (int i=0; i < sorted.length; ++i) {
            assertEquals(sorted[i], starship.getCrew().get(i));
        }
    }
    
    /**
    * Tests App.main with fake crew member. Uses TrackingStream in place of System.out.
     */
    @Test
    public void test_a100_App_main_error() {
        TrackingStream out = new TrackingStream();
        App.out = out;
        String []args = {"--warp", "5.2", "--cloak", "--shields", "7", "--crew", "kirk,gothard,mccoy"};
        App.main(args);
        String expected = "Unrecognized crew member";
        assertTrue("Was " + out.printed, out.printed.contains(expected));
    }   
}
