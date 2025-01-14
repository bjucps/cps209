import app.model.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class WorldTest {

    @BeforeEach
    public void testSetup() {
        World.reset();
    }

    @Test
    public void testCreate() {
        var world = World.instance();

        Critter tracker = world.create(CritterType.TRACKER);

        // Examples to get you started.
        //assertTrue(tracker.getX() < world.getWidth());
        //assertEquals(1, world.getCritters().size());
    }

}
