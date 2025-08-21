import java.io.*;

public class StreamingCSVReaderDemo {
    public static void main(String[] args) throws IOException {
        try (var rd = new StreamingCSVReader("csvjunk.csv", ",")) {
            String[] result = rd.nextLine();
            while (result != null) {
                // process result
                result = rd.nextLine();
            }
        }
    }
}

class StreamingCSVReader implements AutoCloseable {

    private BufferedReader rd;
    private String delimiter;

    public StreamingCSVReader(String filename, String delimiter) throws IOException {
        this.rd = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
    }

    public String[] nextLine() {
        String line;
        try {
            line = rd.readLine();
            if (line != null) {
                return line.split(delimiter);
            }
        } catch (IOException e) {
        }         
        return null;
        
    }

    @Override
    public void close() throws IOException {
        rd.close();
    }

}