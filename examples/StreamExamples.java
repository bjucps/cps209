import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamExamples {

    public static void main(String[] args) {

        StreamExamples examples = new StreamExamples();
        examples.demoDistinctCount();
        examples.demoAllMatch();
        examples.demoAnyMatch();
        examples.demoForeach();
        examples.demoFilter();
        examples.demoMaxAndMin();
        examples.demoMap();
        examples.demoSorted();
    }

    private Scanner scanner;
    private List<String> list;

    public StreamExamples() {
        list = demoGenerate();
        scanner = new Scanner(System.in);
        pause();
    }

    private void pause() {
        scanner.nextLine();
    }

    public void demoAllMatch() {
        final int SIZE = 8;
        System.out.print("allMatch: all less than " + SIZE + "? ");
        var stream = list.stream();
        System.out.print(stream.allMatch(s -> s.length() < SIZE ) + ". ");
        System.out.println(list.stream().filter(s->s.length() >= SIZE).toList());
        pause();
    }

    public void demoDistinctCount() {
        System.out.print("Count: ");
        System.out.print(list.stream().count());

        System.out.print("\tDistinct Count: ");
        System.out.println(list.stream().distinct().count());
        pause();
    }

    public void demoFilter() {
        String letter = "l";
        System.out.print("Filter: contains '" + letter + "': ");
        var result = list.stream().filter(s -> s.toLowerCase().contains(letter));
        System.out.println("result: " + result.toList());
        pause();
    }

    public void demoMap() {
        System.out.print("Map (and filter and peek): ");
        var result = list.stream().filter(s -> s.length() > 5)
                .peek(e -> System.out.print("\n\tFiltered (> 5): " + e))
                .map(s -> s.substring(1, 5).toLowerCase())
                .peek(e -> System.out.print(". Mapped value: " + e))
                .map(s -> new StringBuilder(s).reverse().toString())
                .peek(e -> System.out.print(". Reversed: " + e))
                .collect(Collectors.groupingBy(String::toString));
        System.out.println("\nCollected result: " + result);
        pause();
    }

    public void demoSorted() {
        System.out.print("Sorted, distinct, lowercase:\n");
        list.stream().map(String::toLowerCase).distinct().sorted()
                .forEach(s -> System.out.print(s + " ("+s.charAt(0)+") "));
        pause();
    }

    public void demoAnyMatch() {
        char letter = 'S';
        System.out.print("anyMatch: Do any start with " + letter+"? ");
        System.out.print(list.stream().anyMatch(s -> s.charAt(0) == letter)+". ");
        System.out.println(list.stream().filter(s -> s.charAt(0) == letter).toList());
        
        pause();
    }

    public void demoForeach() {
        System.out.println("Foreach distinct:");
        var stream = list.stream();
        stream.distinct().forEach(s -> System.out.println("\t" + s  + " <=> " + new StringBuilder(s).reverse()));
        pause();
    }

    public void demoMaxAndMin() {
        System.out.print("Max (alphabetically): ");
        System.out.println(list.stream().max((s1, s2) -> s1.compareTo(s2)).get());
        System.out.print("Max (length): ");
        System.out.println(list.stream().max((s1, s2) -> s1.length() - s2.length()).get());
        System.out.print("Max (alphabetically last letter): ");
        System.out.println(list.stream().max((s1, s2) -> s2.charAt(s2.length()-1) - s1.charAt(s1.length()-1)).get());

        System.out.print("Min (alphabetically): ");
        System.out.println(list.stream().min((s1, s2) -> s1.compareTo(s2)).get());
        System.out.print("Min (length): ");
        System.out.println(list.stream().min((s1, s2) -> s1.length() - s2.length()).get());
        System.out.print("Min (alphabetically last letter): ");
        System.out.println(list.stream().min((s1, s2) -> s2.charAt(s2.length()-1) - s1.charAt(s1.length()-1)).get());
        pause();
    }

    public static List<String> demoGenerate() {
        Stream<String> s = Stream.of("David", "Moses", "Jacob", "Saul", "Aaron", 
                           "Abraham", "Solomon", "Joseph", "Paul", "Joshua", "Peter", 
                           "Jeremiah", "Samuel", "Isaac", "Joab", "Zechariah", "Mary", 
                           "Joanna", "Simeon", "Zerubbabel");
        var names = s.toList();
        System.out.println("Choices: " + names);
        var list = Stream.generate(() -> names.get(
            (int) (Math.random() * names.size()))).limit(10).toList();
        System.out.println("Generated: " + list);
        return list;
    }

}
