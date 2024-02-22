import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Tests {
    /**
     * Example test. Expected to throw an Exception. Delete as needed.
     */
    @Test
    public void test_App_main(){
        assertThrows(ExceptionInInitializerError.class,()->new MainWindow().onGreetClicked(null));
    }

}
