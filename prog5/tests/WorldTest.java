import model.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class WorldTest {

    @BeforeEach
    public void testSetup() {
        World.reset();
    }

    @Test
    public void testCreate() {
        var world = World.instance();

        Critter tracker = world.create(CritterType.TRACKER);
        //assertTrue(tracker.getX() < world.getWidth());
        //assertEquals(1, world.getCritters().size());
    }

}
