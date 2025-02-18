package app;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests for Program 1. Uncomment one at a time. Add tests for 100% code coverage.
 * @author Sarah Gothard and ________________________
 */
public class AppTest {

    // @Test
    // public void test_80_Starship_defaultConstructor() {
    //     Starship ship = new Starship();
    //     assertEquals(1.0, ship.getWarp(), .001);
    //     assertFalse(ship.isCloak());
    //     assertEquals(0, ship.getShields());
    //     if (ship.getCrew().get(0).equals("spock")) {
    //         assertEquals("kirk", ship.getCrew().get(1));
    //     } else if (ship.getCrew().get(1).equals("spock")) {
    //         assertEquals("kirk", ship.getCrew().get(0));
    //     } else {
    //         fail("Incorrect default crew: " + ship.getCrew());
    //     }
    // }

    // @Test
    // public void test_80_Starship_setters() {
    //     Starship ship = new Starship();
    //     ship.setWarp(5.0);
    //     assertEquals(5.0, ship.getWarp(), .001);
    //     ship.setCloak(true);
    //     assertTrue(ship.isCloak());
    //     ship.setShields(6);
    //     assertEquals(6, ship.getShields());
    //     ship.setCrew("mccoy,uhura");
    //     assertEquals("mccoy", ship.getCrew().get(0));
    //     assertEquals("uhura", ship.getCrew().get(1));
    // }

    // @Test
    // public void test_80_Starship_factory_valid_input() {
    //     String []args = {"--warp", "5.2", "--cloak", "--shields", "7", "--crew", "kirk,uhura,mccoy"};
    //     Starship ship = Starship.fromArgs(args);
    //     assertEquals(true, ship.isCloak());
    //     assertEquals(5.2, ship.getWarp(), .001);
    //     assertEquals("kirk", ship.getCrew().get(0));
    //     assertTrue(ship.getCrew().get(1).equals("mccoy") || ship.getCrew().get(1).equals("uhura"));
    //     assertTrue(ship.getCrew().get(2).equals("mccoy") || ship.getCrew().get(2).equals("uhura"));
    //     assertEquals(7, ship.getShields());
    // }

    // @Test
    // public void test_80_Starship_factory_valid_input_reversed() {
    //     String []args = {"--crew", "kirk,uhura,mccoy", "--shields", "7", "--cloak", "--warp", "5.2"};
    //     Starship ship = Starship.fromArgs(args);
    //     assertEquals(true, ship.isCloak());
    //     assertEquals(5.2, ship.getWarp(), .001);
    //     assertEquals("kirk", ship.getCrew().get(0));
    //     assertTrue(ship.getCrew().get(1).equals("mccoy") || ship.getCrew().get(1).equals("uhura"));
    //     assertTrue(ship.getCrew().get(2).equals("mccoy") || ship.getCrew().get(2).equals("uhura"));
    //     assertEquals(7, ship.getShields());
    // }

    // @Test
    // public void test_80_Starship_factory_no_input() {
    //     String []args = {};
    //     Starship ship = Starship.fromArgs(args);
    //     assertEquals(false, ship.isCloak());
    //     assertEquals(1, ship.getWarp(), .001);
    //     assertTrue(ship.getCrew().get(0).equals("kirk") || ship.getCrew().get(0).equals("spock"));
    //     assertTrue(ship.getCrew().get(1).equals("kirk") || ship.getCrew().get(1).equals("spock"));
    //     assertEquals(0, ship.getShields());
    // }


    // /**
    //  * Tests Starship fromArgs with incorrect argument: --wrap
    //  */
    // @Test
    // public void test_80_Starship_factory_invalid_input() {
    //     String []args = {"--warp", "5.2", "--cloak", "--wrap", "7", "--crew", "kirk,uhura,mccoy"};
    //     Exception e = assertThrows(IllegalArgumentException.class, 
    //                             () -> Starship.fromArgs(args));
    //     assertTrue(e.getMessage().contains("is not valid"));
    // }

    // /**
    //  * Tests App.main using example input. Uses TrackingStream in place of System.out.
    //  */
    // @Test
    // public void test_80_App_main() {
    //     TrackingStream out = new TrackingStream();
    //     System.setOut(out);

    //     String []args = {"--warp", "5.2", "--cloak", "--shields", "7", "--crew", "kirk,uhura,mccoy"};
    //     App.main(args);
    //     String expected = "Shields:\t[XXXXXXX--]" + System.lineSeparator() +
    //             "Warp:\t[5.2]" + System.lineSeparator() + "Cloak:\t[Engaged]" +
    //             System.lineSeparator() + "Bridge Crew:" + 
    //             System.lineSeparator() + "* kirk" + System.lineSeparator() +
    //             "* mccoy" + System.lineSeparator() + "* uhura" + 
    //             System.lineSeparator();
    //     assertEquals(expected, out.printed);
    // }

    // @Test
    // public void test_80_App_main_fullshields() {
    //     TrackingStream out = new TrackingStream();
    //     System.setOut(out);
    //     String []args = {"--warp", "0", "--shields", "9", "--crew", "chekov,scotty,spock"};
    //     App.main(args);
    //     String expected = "Shields:\t[XXXXXXXXX]" + System.lineSeparator() +
    //             "Warp:\t[0.0]" + System.lineSeparator() + "Cloak:\t[Disengaged]" +
    //             System.lineSeparator() + "Bridge Crew:" + 
    //             System.lineSeparator() + "* chekov" + System.lineSeparator() +
    //             "* scotty" + System.lineSeparator() + "* spock" + 
    //             System.lineSeparator();
    //     assertEquals(expected, out.printed);
    // }

    // @Test
    // public void test_80_App_main_noshields() {
    //     TrackingStream out = new TrackingStream();
    //     System.setOut(out);
    //     String []args = {"--warp", "15", "--crew", "spock"};
    //     App.main(args);
    //     String expected = "Shields:\t[---------]" + System.lineSeparator() +
    //             "Warp:\t[15.0]" + System.lineSeparator() + "Cloak:\t[Disengaged]" +
    //             System.lineSeparator() + "Bridge Crew:"  + System.lineSeparator() +
    //              "* spock" + System.lineSeparator();
    //     assertEquals(expected, out.printed);
    // }

    // /**
    //  * Runs the program like the user would.
    //  */
    // @Test
    // public void test_80_run_process()    {
    //     String []args = {"--cloak", "--warp", "3.14", 
    //               "--shields", "1", "--crew", "kirk,mccoy,scotty"};
    //         TrackingStream out = new TrackingStream();
    //         System.setOut(out);
    //         App.main(args);
    //         assertTrue(out.printed.contains("Shields:\t[X--------]"));
    //         assertTrue(out.printed.contains("Warp:\t[3.14]"));
    //         assertTrue(out.printed.contains("Cloak:\t[Engaged]"));
    //         assertTrue(out.printed.contains("Bridge Crew:"));
    //         assertTrue(out.printed.contains("* kirk"));
    //         assertTrue(out.printed.contains("* mccoy"));
    //         assertTrue(out.printed.contains("* scotty"));
            
    // }

    // /**
    //  * Checks if any method in src or tests is more than 30 lines.
    //  */
    // @Test
    // public void test_80_method_too_long() {
    //     File f = new File(System.getProperty("user.dir"));
    //     LongMethodChecker.checkFiles(f.listFiles());
    // }

    
    // /**
    //  * Included to get full coverage from jacoco. App has an implicit default constructor.
    //  */
    // @Test
    // public void test_80_App_constructor() {
    //     App app = new App();
    //     assertNotNull(app);
    // }

    // @Test
    // public void test_90_Starship_factory_negative_warp() throws Exception {
    //     String []args = {"--warp", "-.1"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));

    // }

    // @Test
    // public void test_90_Starship_factory_too_high_warp() {
    //     String []args = {"--warp", "15.1"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));

    // }

    // @Test
    // public void test_90_Starship_factory_wrong_type_warp() {
    //     String []args = {"--warp", "hello"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }
    
    // @Test
    // public void test_90_Starship_factory_negative_shields() {
    //     String []args = {"--shields", "-1"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }

    // @Test
    // public void test_90_Starship_factory_too_high_shields() {
    //     String []args = {"--shields", "10"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }

    // @Test
    // public void test_90_Starship_factory_wrong_type_shields() {
    //     String []args = {"--shields", "4.5"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }

    // @Test
    // public void test_90_Starship_factory_missing_value_shields() {
    //     String []args = {"--shields"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }


    // @Test
    // public void test_90_Starship_factory_missing_value_warp() {
    //     String []args = {"--warp"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }

    // @Test
    // public void test_90_Starship_factory_missing_value_crew() {
    //     String []args = {"--crew"};
    //     assertThrows(IllegalArgumentException.class, () -> Starship.fromArgs(args));
    // }


    // @Test
    // public void test_a100_Starship_factory_sorted_crew() {
    //     String []args = {"--crew", "kirk,spock,mccoy,uhura,scotty,chekov"};
    //     String [] sorted = {"chekov", "kirk", "mccoy", "scotty", "spock", "uhura" };
    //     Starship starship = Starship.fromArgs(args);
    //     for (int i=0; i < sorted.length; ++i) {
    //         assertEquals(sorted[i], starship.getCrew().get(i));
    //     }
    // }
    
    // /**
    // * Tests App.main with fake crew member. Uses TrackingStream in place of System.out.
    //  */
    // @Test
    // public void test_a100_App_main_error() {
    //     TrackingStream out = new TrackingStream();
    //     System.setErr(out);
    //     System.setOut(out);
    //     String []args = {"--warp", "5.2", "--cloak", "--shields", "7", "--crew", "kirk,gothard,mccoy"};
    //     App.main(args);
    //     String expected = "Unrecognized crew member";
    //     assertTrue(out.printed.contains(expected), "Was " + out.printed);
    // }
}
