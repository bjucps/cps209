import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamExamples {

    public static void main(String[] args) {

        Scanner pause = new Scanner(System.in);
        var list = StreamExamples.demoGenerate();
        System.out.println("Starting list:");
        list.stream().forEach(e->System.out.print(e +"\t"));
        System.out.println();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoAllMatch();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoOf();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoAnyMatch();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoForeach();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoDistinctCount();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoFilter();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoMaxAndMin();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoMap();
        System.out.print("Press enter to continue...");
        pause.nextLine();
        demoSorted();
        pause.close();
    }

    public static void demoAllMatch() {
        System.out.println("\n\tAll Match");
        var stream = demoGenerate().stream();
        int length = 5;
        System.out.print("All Strings > " + length + "? ");
        System.out.println(stream.allMatch(s -> s.length() > length));
    }

    public static void demoDistinctCount() {
        System.out.print("\n\tCount: ");
        var list = demoGenerate();
        System.out.println(list.stream().count());

        System.out.print("\tDistinct Count: ");
        System.out.println(list.stream().distinct().count());
    }

    public static void demoFilter() {
        char letter = 'r';
        System.out.print("\n\tFilter on contains '" + letter + "': ");
        var stream = demoGenerate().stream();
        var result = stream.filter(s -> s.toLowerCase().contains(String.valueOf(letter)));
        System.out.println("result: " + result.toList());
    }

    public static void demoOf() {
        System.out.print("\n\tof (list from author names): ");
        var result = Stream.of("Matthew", "Mark", "Luke", "John", "Paul", "Peter", "James");
        System.out.println("result: " + result.toList());
    }

    public static void demoMap() {
        System.out.print("\n\tMap (and filter and peek): ");
        var result = demoGenerate().stream().filter(s -> s.length() > 0 && Character.isLowerCase(s.charAt(0)))
                .peek(e -> System.out.println("Filtered value (starts with lower case): " + e))
                .map(String::toUpperCase) // same as map(s -> s.toUpperCase())
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println("Collected result: " + result);
    }

    public static void demoSorted() {
        System.out.print("\n\tSorted, distinct, lowercase:\n");
        demoGenerate().stream().distinct() // .peek(s->System.out.println("*" + s))
                .sorted() // .peek(s->System.out.println("[" + s + "]"))
                .map(String::toLowerCase).forEach(s -> System.out.println("item: " + s));
    }

    public static void demoAnyMatch() {

        System.out.print("\n\tAny match: Is anything > 8? ");
        System.out.println(demoGenerate().stream().anyMatch(s -> s.length() > 8));
    }

    public static void demoForeach() {
        System.out.println("\n\tForeach");
        var stream = demoGenerate().stream();
        stream.forEach(s -> {
            var sb = new StringBuilder(s);
            sb.reverse();
            System.out.println("Reversed: " + sb);
        });
    }

    public static void demoMaxAndMin() {
        System.out.print("\n\tMax (alphabetically): ");
        var list = demoGenerate();
        System.out.println(list.stream().max((s1, s2) -> s1.compareTo(s2)));

        System.out.print("\n\tMin (alphabetically) with get(): ");
        System.out.println(list.stream().min((s1, s2) -> s1.compareTo(s2)).get());

        System.out.print("\n\tMax (length) with get(): ");
        System.out.println(list.stream().max((s1, s2) -> s1.length() - s2.length()).get());

        System.out.print("\n\tMin (length): ");
        System.out.println(list.stream().min((s1, s2) -> s1.length() - s2.length()));

    }

    public static List<String> demoGenerate() {
        var names = Stream.of("David", "Joey", "Jon", "David", "Joey", "Jon", 
                        "Grace", "Martin", "Robert", "julia heying", "martin", "Pierre",
                        "micah", "Matthew", "caleb", "Josiah", "jun", "").toList();
        return Stream.generate(() -> names.get(
                (int) (Math.random() * names.size()) // picking a number [0, size-1]
        )).limit(20).toList();

    }

}