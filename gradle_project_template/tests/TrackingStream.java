import java.io.PrintStream;

/**
 * Overrides PrintStream in order to track all printing for testing purposes.
 *     Can be used instead of System.out. Results still print to screen
 *     and are also logged.
 * @author Sarah Gothard, 2023  
 */
class TrackingStream extends PrintStream {
    public String printed = "";

    public TrackingStream() {
        super(System.out);
    }

    @Override
    public void print(String str) {
        String toPrint = standardizeEOLcharacters(str);
        super.print(toPrint);
        printed += toPrint;
    }   

    @Override
    public void println(String str) {
        print(str + System.lineSeparator());
    }   

    @Override
    public void print(Object obj) {
        print(obj.toString());
    }   

    @Override
    public void println(Object obj) {
        println(obj.toString());
    }   

    private static String standardizeEOLcharacters (Object objToPrint) {
        StringBuilder sbToPrint = new StringBuilder(objToPrint.toString());
        correctBackslashRs(sbToPrint);
        correctBackslashNs(sbToPrint);
        correctBackslashRNs(sbToPrint);
        return sbToPrint.toString();
    }

    private static void correctBackslashRs(StringBuilder sbToPrint) {
        final String EOL_CHAR = "\r";
        if (!System.lineSeparator().equals(EOL_CHAR)) {
            int loc = sbToPrint.indexOf(EOL_CHAR, 0);
            int lengthDiff = System.lineSeparator().length() - EOL_CHAR.length();
            while (loc >= 0) {
                if (loc == sbToPrint.length() - 1 || sbToPrint.charAt(loc + 1) != '\n') {
                    sbToPrint.deleteCharAt(loc);
                    sbToPrint.insert(loc, System.lineSeparator());
                }
                loc = sbToPrint.indexOf(EOL_CHAR, loc + lengthDiff);
            }
        }
    }

    private static void correctBackslashNs(StringBuilder sbToPrint) {
        final String EOL_CHAR = "\n";
        if (!System.lineSeparator().equals(EOL_CHAR)) {
            int loc = sbToPrint.indexOf(EOL_CHAR, 0);
            int lengthDiff = System.lineSeparator().length() - EOL_CHAR.length();
            while (loc >= 0) {
                if (loc == 0 || sbToPrint.charAt(loc - 1) != '\r') {
                    sbToPrint.deleteCharAt(loc);
                    sbToPrint.insert(loc, System.lineSeparator());
                }
                loc = sbToPrint.indexOf(EOL_CHAR, loc + lengthDiff);
            }
        }
    }

    private static void correctBackslashRNs(StringBuilder sbToPrint) {
        final String EOL_CHAR = "\r\n";
        if (!System.lineSeparator().equals(EOL_CHAR)) {
            int loc = sbToPrint.indexOf(EOL_CHAR, 0);
            int lengthDiff = System.lineSeparator().length() - EOL_CHAR.length();
            while (loc >= 0) {
                sbToPrint.deleteCharAt(loc);
                sbToPrint.insert(loc, System.lineSeparator());
                loc = sbToPrint.indexOf(EOL_CHAR, loc + lengthDiff);
            }
        }
    }
}
