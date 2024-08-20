import java.io.PrintStream;

/**
 * Overrides PrintStream in order to track all printing for testing purposes.
 *     Can be used instead of System.out. Results still print to screen
 *     and are also logged.
 * @author Sarah Gothard, 2024
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
        doCorrection(sbToPrint, "\r", '\n', 1);
    }

    private static void correctBackslashNs(StringBuilder sbToPrint) {
        doCorrection(sbToPrint, "\n", '\r', -1);
    }

    private static void correctBackslashRNs(StringBuilder sbToPrint) {
        doCorrection(sbToPrint, "\r\n", '\0', 0);
    }


    private static boolean notPartOfPair(StringBuilder sbToPrint, char related, 
                                         int loc, int offset) {
        return loc+offset == sbToPrint.length() || loc+offset < 0 || 
                sbToPrint.charAt(loc + offset) != related;
    }


    private static void doCorrection(StringBuilder sbToPrint, String eolChar,
                                     char nearby, int offset) {
        if (!System.lineSeparator().equals(eolChar)) {
            int loc = sbToPrint.indexOf(eolChar, 0);
            int lengthDiff = System.lineSeparator().length() - eolChar.length();
            while (loc >= 0) {
                if (notPartOfPair(sbToPrint, nearby, loc, offset)) {
                    sbToPrint.deleteCharAt(loc);
                    sbToPrint.insert(loc, System.lineSeparator());
                }
                loc = sbToPrint.indexOf(eolChar, loc + lengthDiff);
            }
        }
    }
}
