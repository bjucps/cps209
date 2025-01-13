public class App
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("Usage: arun <num> [<num>...]");
            System.exit(1);
        }

        int sum = 0;
        for (String item : args)
        {
            sum += Integer.parseInt(item);
        }

        System.out.println("Sum of arguments: " + sum);

    }
}   