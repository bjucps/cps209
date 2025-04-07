package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ModelTest {
    private static TestObserver obs;
    @BeforeAll
    public static void setup() {
        obs = new TestObserver();
    }

    @Test
    public void test_Model_instance(){
        assertNotNull(Model.getInstance());
    }
    @Test
    public void test_Model_generateMove(){
        Model.getInstance().addObserver(obs);
        int obsCount = obs.getHasMovedCount();
        Model.getInstance().generateMove();
        assertEquals(obsCount+1, obs.getHasMovedCount());
    }

    @Test
    public void test_Model_TwiceObserver(){
        Model.getInstance().addObserver(obs);
        Model.getInstance().addObserver(obs);
        int obsCount = obs.getHasMovedCount();
        Model.getInstance().generateMove();
        assertEquals(obsCount+1, obs.getHasMovedCount());
    }
    @Test
    public void test_Model_GameOver(){
        Model.getInstance().addObserver(obs);
        int obsCount = obs.getGameOverCount();
        do { 
            Model.getInstance().generateMove();    
        } while (obs.getLastMove() != 10);
        
        assertEquals(obsCount+1, obs.getGameOverCount());
    }
}


