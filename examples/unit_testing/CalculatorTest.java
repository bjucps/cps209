import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void testAdd_PositiveNums_PositiveResult() {
        var calc = new Calculator();
        calc.add(5);
        assertEquals(3, calc.getCurrent());
    }

    @Test
    public void testAdd_HugeNum_ThrowsException() {
        var calc = new Calculator(2000000000);
        Exception e;
        e = assertThrows (IllegalArgumentException.class,
                        () -> calc.add(2000000000));
        assertTrue(e.getMessage().contains("Argument too large"));
    }  
}