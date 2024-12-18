import java.util.Random;

public class Colour {
    private static final String[] Colour = {
            "\u001B[91m", // Bright Red
            "\u001B[92m", // Bright Green
            "\u001B[93m", // Bright Yellow
            "\u001B[94m", // Bright Blue
            "\u001B[95m", // Bright Purple
            "\u001B[96m", // Bright Cyan
            "\u001B[97m"  // Bright White
    };

    private static final Random random = new Random();

    public static void printRandomColor(String Word) {
        String randomColor = Colour[random.nextInt(Colour.length)];
        System.out.print(randomColor + Word + "\u001B[0m"); //Reset the color
    }

    public static void printlnRandomColor(String Wordln) {
        String randomColor = Colour[random.nextInt(Colour.length)];
        System.out.println(randomColor + Wordln + "\u001B[0m"); //Reset the color
    }
}
// End of Colour Class