import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuessTest
{  
    @Test
    public void testCheck_LowGuess_ReportsTooLow()
    {
        var g = new Guess();
        g.setSecret(5);
        String result = g.check(7);
        assertEquals("Too Low.", result);
    }

}
