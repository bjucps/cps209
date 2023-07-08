import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface CallbackAction {
    void callback(String msg);
}

public class Searcher {


    public void doSearch(String filename, String word) {
        int occurrences = 0;
        try (var rd = new BufferedReader(new FileReader(filename))) {
            String line = rd.readLine();
            int lines = 0;
            while (line != null) {
                int delay = 0;

                // Insert delay to help us see what's going on
                while (delay < 100) System.out.print(delay++);

                ++lines;
                if (lines % 10 == 0) {
                    // TODO: report progress
                }
                if (line.contains(word)) {
                    ++occurrences;                    
                }
                line = rd.readLine();
            }


            // TODO: report completion
            
        } catch (IOException e) {
            // TODO: report error

        }
    }

    
    
}
