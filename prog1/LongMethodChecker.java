package app;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// belongs in the test folder

/**
 * Checks if the specified file has any methods that are longer than permitted.
 * @author Sarah Gothard, 2023  
 */
public class LongMethodChecker {

    private int openedBraces, linesInMethod, lineNumber;
    private int maxLines = 30;

    /**
     * Recursively checks the length of methods in all java files.
     * @param files list of files to check
     */
    public static void checkFiles(File [] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                checkFiles(file.listFiles());
            } else if (file.getName().endsWith(".java")) {
                new LongMethodChecker(file);
            }
        }
    }
    

    public LongMethodChecker (File f, int maxLines) {
        this.maxLines = maxLines;
        checkMethodLength(f);
    }

    public LongMethodChecker (File f) {
        checkMethodLength(f);
    }

    public void checkMethodLength(File f) {
        openedBraces = 0;
        linesInMethod = 0;
        lineNumber = 1;
        try (BufferedReader file = new BufferedReader(new FileReader(f))) {

            String line;
            while ((line = file.readLine()) != null) {
                processLineAndFailIfMethodTooLong(line, f.getName());
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    private boolean isFirstLineOfMethod(int changeInBraces) {
        return changeInBraces > 0 && openedBraces == 2;
    }

    private boolean isLastLineOfMethod(int changeInBraces) {
        return changeInBraces < 0 && openedBraces == 1;
    }

    public void processLineAndFailIfMethodTooLong(String line, String filename) {
        int openCloseBraceStatus = getOpenCloseBraceStatus(line);
        openedBraces += openCloseBraceStatus;
        if (isLastLineOfMethod(openCloseBraceStatus) && linesInMethod > maxLines) 
            fail("Method ending at " + lineNumber + " in " +  
                    filename + " is longer than " + maxLines + " lines.");
        else if (isFirstLineOfMethod(openCloseBraceStatus)) 
            linesInMethod = 0;

        ++linesInMethod;
        ++lineNumber;
    }

    private static int getOpenCloseBraceStatus (String line) {
        int countOpen = countOccurrences(line, "{");
        int countClose = countOccurrences(line, "}");
        return countOpen - countClose;          
    }

    private static int countOccurrences(String line, String itemToFind) {
        int countOpen = line.length() - line.replace(itemToFind, "").length();
        return countOpen;
    }
}
