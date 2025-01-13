package app;

public class App
{
    public static void main(String[] args)
    {
        if (args.length < 1) {
            System.out.println("Usage: arun <num> [<num>...]");
        } else {
            System.out.println("Sum of arguments: " + getSum(args));
        }     
    }

    public static int getSum(String[] args) {
        int sum = 0;
        for (String item : args) {
            sum += Integer.parseInt(item);
        }
        return sum;
    }
}   