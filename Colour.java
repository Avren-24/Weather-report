import java.util.Random;

public class Colour {
    private static final String[] COLOURS = {
            "\u001B[91m", // Bright Red
            "\u001B[92m", // Bright Green
            "\u001B[93m", // Bright Yellow
            "\u001B[94m", // Bright Blue
            "\u001B[95m", // Bright Purple
            "\u001B[96m", // Bright Cyan
            "\u001B[97m"  // Bright White
    };

    private static final Random random = new Random();

    public static void printRandomColor(String word) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.print(randomColor + word + "\u001B[0m");
    }

    public static void printlnRandomColor(String wordln) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.println(randomColor + wordln + "\u001B[0m");
    }

    public static void printRandomColorForPattern(String pattern) {
        StringBuilder result = new StringBuilder();
        for (char signal : pattern.toCharArray()) {
                String randomColor = COLOURS[random.nextInt(COLOURS.length)];
                result.append(randomColor).append(signal).append("\u001B[0m");
        }
        System.out.println(result.toString());
    }
}
// End of Colour Class
