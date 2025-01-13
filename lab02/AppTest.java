package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class AppTest {
    /**
     * Addition test
     */
    @Test
    public void test_getSum(){
        String []args = {"3", "4", "13"};
        
        assertEquals(App.getSum(args), 20);
    }

    /**
     * Addition test: empty
     */
    @Test
    public void test_add_empty(){
        String []args = {};
        assertEquals(App.getSum(args), 0);
    }

    /**
     * Addition test: nonnumeric
     */
    @Test
    public void test_add_nonNumberic(){
        String []args = {"hello", "world"};
        assertThrows(NumberFormatException.class, () ->App.getSum(args));
    }

}
