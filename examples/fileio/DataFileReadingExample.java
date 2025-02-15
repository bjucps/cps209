import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Example reading binary data. Handy for Program 2.
 */
public class DataFileReadingExample {
    private final BufferedInputStream inputStream;
    public DataFileReadingExample(String filename) throws IOException {
        inputStream = new BufferedInputStream(new FileInputStream (filename));
    }
    public byte[] read2Bytes () throws IOException {
        byte[] twoBytes = new byte[2];
        if (inputStream.read(twoBytes) != 2) {
            throw new IOException("No more bytes");
        }
        return twoBytes;
    }

    public void close () throws IOException {
        inputStream.close();
    }

    public static void main(String[] args) {
        try {
            var reader = new DataFileReadingExample("demo.mp3");
            byte [] twoBytes = reader.read2Bytes();
            System.out.println("Read in " + new String(twoBytes));
            twoBytes =reader.read2Bytes();
            System.out.println("Read in " + new String(twoBytes));
        } catch (IOException e) {
            System.err.println("Didn't work: " + e);
        }
    }
}
