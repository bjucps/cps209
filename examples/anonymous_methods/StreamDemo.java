import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Program {

    static boolean isLarge(String s) {
        return s.length() > 3;
    }

    public static void main(String[] args) {
        var items = new ArrayList<String>(Arrays.asList("RED", "green", "blue", "green", ""));

        String result = "";
        for (String item : items) {
            if (item.length() > 3) {
                result = item;
                break;
            }
        }
        System.out.println("First item with length > 3: " + result);

        long distinctCount = items.stream().distinct().count();
        String themax = items.stream().max((o1, o2) -> o1.length() - o2.length()).get();

        int[] nums = { 5, 10, -3, 36, 19 };
        //int largestNum = Stream.of(nums).max((o1, o2) -> o1 - o2).get();

        items.stream().filter( s -> s.length() > 3).forEach( item -> System.out.println(item));
        var itemsLongerThan3 = items.stream().filter( s -> s.length() > 3).collect(Collectors.toList());


        System.out.println("The longest item is: " + themax);

        System.out.println("There are " + distinctCount + " distinct items.");

        var students = new ArrayList<Student>(
                Arrays.asList(new Student("Steve", 15), new Student("George", 18), new Student("Amy", 16)));

        students.sort((o1, o2) -> o1.name.compareTo(o2.name));

    }

}

class Student {
    public String name;
    public int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }

}
