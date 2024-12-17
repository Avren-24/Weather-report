import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WeatherApp {

    WeatherStore weatherStore;
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        WeatherApp WeatherApp = new WeatherApp();
        WeatherApp.setup();
        WeatherApp.clearScreen();
        WeatherApp.runMenu();
    }

    private int displayMenu() {
        clearScreen(); // Only works at console
        System.out.println("/////////////////////////");
        System.out.print("""
               Weather Forecast Menu
               ---------------------
                  1) List All Weather Forecasts
                  2) Add a Weather Forecast
                  3) Search for Weather Forecast (Region and Date)
                  4) Get Behavioral Advice
                  5) Update Weather Condition
                  6) Update Temperature
                  0) Exit
               ==>>""");
        int option = input.nextInt();
        System.out.println("/////////////////////////");
        return option;
    }

    private void runMenu() {
        int option = displayMenu();

        while (option != 0) {
            switch (option) {
                case 1 -> printWeatherForecasts();
                case 2 -> addWeatherForecast();
                case 3 -> findWeatherForecast();
                case 4 -> getAdvice();
                case 5 -> updateCondition();
                case 6 -> updateTemperature();
                case -88 -> dummyData();
                default -> System.out.println("Invalid option entered: " + option);
            }

            // Pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress enter key to continue... ");
            input.nextLine();
            input.nextLine(); // Second read is required - bug in Scanner class

            // Display the main menu again
            option = displayMenu();
        }

        // The user chose option 0, so exit the program
        System.out.println("Exiting System...bye... ");
        System.out.println("Thank you for using our Weather Forecast App V1.0.");
        System.exit(0);
    }

    private void updateTemperature() {
        input.nextLine(); // Bug fix
        System.out.print("Enter Region : ");
        String region = input.nextLine();
        System.out.print("Enter Date (yyyy-MM-dd) : ");
        String date = input.nextLine();
        System.out.print("Enter New Temperature : ");
        int temperature = input.nextInt();

        boolean isChanged = weatherStore.updateTemperature(temperature, region, date);

        if (isChanged) {
            System.out.println("Update Successful... ");
        } else {
            System.out.println("Update NOT Successful... ");
        }
    }

    private void updateCondition() {
        input.nextLine(); // Bug fix
        System.out.print("Enter Region : ");
        String region = input.nextLine();
        System.out.print("Enter Date (yyyy-MM-dd) : ");
        String date = input.nextLine();
        System.out.print("Enter New Condition (Sunny or Rainy) : ");
        String condition = input.nextLine();

        boolean isChanged = weatherStore.updateCondition(condition, region, date);

        if (isChanged) {
            System.out.println("Update Successful... ");
        } else {
            System.out.println("Update NOT Successful... ");
        }
    }

    private void getAdvice() {
        System.out.println("///////////////////////////////////////");
        System.out.println("Get Behavioral Advice");
        System.out.println("///////////////////////////////////////");
        input.nextLine(); // Required for bug in Scanner Class
        System.out.print("Enter Region to Search for =>>");
        String region = input.nextLine();
        System.out.print("Enter Date (yyyy-MM-dd) to Search for =>>");
        String date = input.nextLine();

        String advice = weatherStore.getAdvice(region, date);
        System.out.println(advice);
    }

    private void findWeatherForecast() {
        System.out.println("///////////////////////////////////////");
        System.out.println("Search Weather Forecast");
        System.out.println("///////////////////////////////////////");
        input.nextLine(); // Required for bug in Scanner Class
        System.out.print("Enter Region to Search for =>>");
        String region = input.nextLine();
        System.out.print("Enter Date (yyyy-MM-dd) to Search for =>>");
        String date = input.nextLine();

        WeatherDay foundWeather = weatherStore.find(region, date);

        if (foundWeather != null) {
            System.out.println(foundWeather);
        } else {
            System.out.println("No Weather Forecast found for Region: " + region + " and Date: " + date);
        }
    }

    private void addWeatherForecast() {
        System.out.println("Please Enter Weather Forecast Details... ");

        input.nextLine(); // Required for bug in Scanner Class
        System.out.print("Enter Region : ");
        String region = input.nextLine();
        System.out.print("Enter Date (yyyy-MM-dd) : ");
        String date = input.nextLine();
        System.out.print("Enter Weather Condition(Sunny or Rainy) : ");
        String WeatherCondition = input.nextLine();
        System.out.print("Enter Temperature : ");
        int temperature = input.nextInt();
        input.nextLine(); // Extra read for bug in Scanner Class
        System.out.print("Enter Day Name(From Monday to Sunday) : ");
        String dayName = input.nextLine();
        System.out.print("Enter Weather Description : ");
        String weatherDescription = input.nextLine();

        boolean isAdded = weatherStore.add(new WeatherDay(region, date, WeatherCondition, temperature, dayName, weatherDescription));
        if (isAdded) {
            System.out.println("Weather Forecast Added Successfully... ");
        } else {
            System.out.println("No Weather Forecast Added... ");
        }
    }

    private void printWeatherForecasts() {
        System.out.println();
        System.out.println("///////////////////////");
        System.out.println("////Weather Forecasts////");
        System.out.println("///////////////////////");
        System.out.println();
        System.out.println(weatherStore.getListWeather());
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void setup() {
        System.out.println("///////////////////////////////////////");
        System.out.println("Weather Forecast App V1.0.");
        System.out.println("///////////////////////////////////////");
        System.out.println();
        System.out.println();
        System.out.println("Welcome to our Weather Forecast App V1.0.");
        System.out.print("Please wait while the system loads...");
        try {
            System.out.print("...");
            TimeUnit.SECONDS.sleep(1);
            System.out.print("...");
            TimeUnit.SECONDS.sleep(1);
            System.out.print("...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("...");
            System.out.println();
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        weatherStore = new WeatherStore();
    }

    public void dummyData() {
        WeatherDay w1 = new WeatherDay("Nanjing",
                "2024-11-01",
                "Sunny",
                20,
                "Monday",
                "Wow! The day is so nice!");
        weatherStore.add(w1);

        WeatherDay w2 = new WeatherDay("Beijing",
                "2024-11-02",
                "Rainy",
                15,
                "Tuesday",
                "Oh! It feels like it's raining cats and dogs!");
        weatherStore.add(w2);
    }
}