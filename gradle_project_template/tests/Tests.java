import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Tests {
    /**
     * Addition test
     */
    @Test
    public void test_add(){
        String []args = {"3", "4", "13"};
        assertEquals(App.addArgs(args), 20);
    }

    /**
     * Addition test: empty
     */
    @Test
    public void test_add_empty(){
        String []args = {};
        assertEquals(App.addArgs(args), 0);
    }

        /**
     * Addition test: nonnumeric
     */
    @Test
    public void test_add_nonNumberic(){
        String []args = {"hello", "world"};
        assertThrows(NumberFormatException.class, () ->App.addArgs(args));
    }

}
