package app;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrackingStreamTest {
    @Test
    public void test_trackingStream_println () {
        TrackingStream out = new TrackingStream();
        out.println("Hello World");
        String expected = "Hello World" + System.lineSeparator();
        assertEquals(expected, out.printed, out.printed + " instead of " + expected);
        out.close();
    }
    @Test
    public void test_trackingStream_Object_print () {
        TrackingStream out = new TrackingStream();
        Integer i = 42;
        out.print(i);
        String expected = "42";
        assertEquals(expected, out.printed);
        out.close();
    }
    @Test
    public void test_trackingStream_Object_println () {
        TrackingStream out = new TrackingStream();
        Integer i = 42;
        out.println(i);
        String expected = "42" + System.lineSeparator();
        assertEquals(expected, out.printed);
        out.close();
    }

    @Test
    public void test_trackingStream_convert_backslash_n () {
        TrackingStream out = new TrackingStream();
        out.print("Hello\nWorld");
        String expected = "Hello" + System.lineSeparator() + "World";
        assertEquals(expected, out.printed);
        out.close();
    }
    @Test
    public void test_trackingStream_convert_backslash_r () {
        TrackingStream out = new TrackingStream();
        out.print("Hello\rWorld");
        String expected = "Hello" + System.lineSeparator() + "World";
        assertEquals(expected, out.printed);
        out.close();
    }
    @Test
    public void test_trackingStream_convert_backslash_rn () {
        TrackingStream out = new TrackingStream();
        out.print("Hello\r\nWorld");
        String expected = "Hello" + System.lineSeparator() + "World";
        assertEquals(expected, out.printed);
        out.close();
    }

    @Test
    public void test_trackingStream_convert_backslash_ns () {
        TrackingStream out = new TrackingStream();
        out.print("\nHello\nWorld\n\n\n");
        String expected = System.lineSeparator() + "Hello" + System.lineSeparator() + "World"+ System.lineSeparator()+ System.lineSeparator()+ System.lineSeparator();
        assertEquals(expected, out.printed);
        out.close();
    }
    @Test
    public void test_trackingStream_convert_backslash_rs () {
        TrackingStream out = new TrackingStream();
        out.print("\r\rHello\rWorld\r\r");
        String expected = System.lineSeparator() + System.lineSeparator() + "Hello" + System.lineSeparator() + "World"+ System.lineSeparator()+ System.lineSeparator();
        assertEquals(expected, out.printed);
        out.close();
    }
    @Test
    public void test_trackingStream_convert_backslash_rns () {
        TrackingStream out = new TrackingStream();
        out.print("\r\n\r\nHello\r\nWorld\r\n\r\n");
        String expected = System.lineSeparator()+ System.lineSeparator() + "Hello" + System.lineSeparator() + "World"+ System.lineSeparator()+ System.lineSeparator();
        assertEquals(expected, out.printed);
        out.close();
    }

}
