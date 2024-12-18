
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WeatherApp {

    private final WeatherStore weatherStore;

    public WeatherApp() {
        this.weatherStore = new WeatherStore();
    }
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        WeatherApp weatherApp = new WeatherApp();
        weatherApp.dummyData();
        weatherApp.setup();
        weatherApp.clearScreen();
        weatherApp.runMenu();
    }

    private int displayMenu() {
        clearScreen(); // Only works at console
        Colour.printlnRandomColor("/////////////////////////");
        Colour.printlnRandomColor("""
       Weather Forecast Menu
       ---------------------
          1) Add a Weather Forecast
          2) Update Weather Condition
          3) Update Temperature
          4) Delete Weather Forecast
          5) Search for Weather Forecast (Region and Date)
          6) List Weather Forecasts by Region
          7) List Weather Forecasts by Date
          8) List All Weather Forecasts
          9) Get Behavioral Advice
          0) Exit
       ==>>""");
        int option = input.nextInt();
        Colour.printlnRandomColor("/////////////////////////");
        return option;
    }

    private void runMenu() {
        int option = displayMenu();

        while (option != 0) {
            switch (option) {
                case 1 -> addWeatherForecast();
                case 2 -> updateCondition();
                case 3 -> updateTemperature();
                case 4 -> deleteWeatherForecast();
                case 5 -> findWeatherForecast();
                case 6 -> listWeatherForecastsByRegion();
                case 7 -> listWeatherForecastsByDate();
                case 8 -> printWeatherForecasts();
                case 9 -> getAdvice();
                case -88 -> dummyData();
                default -> Colour.printlnRandomColor("Invalid option entered: " + option);
            }

            // Pause the program so that the user can read what we just printed to the terminal window
            Colour.printlnRandomColor("\nPress enter key to continue... ");
            input.nextLine();
            input.nextLine(); // Second read is required - bug in Scanner class

            // Display the main menu again
            option = displayMenu();
        }

        // The user chose option 0, so exit the program
        Colour.printlnRandomColor("Exiting System...bye... ");
        Colour.printlnRandomColor("Thank you for using our Weather Forecast App V1.0.");
        Colour.printlnRandomColor("The program ends at: " + getLocalDateTime());
        System.exit(0);
    }

    private void deleteWeatherForecast() {
        input.nextLine(); // Required for bug in Scanner Class
        Colour.printlnRandomColor("Enter Region: ");
        String region = input.nextLine();
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd): ");
        String date = input.nextLine();

        boolean isDeleted = weatherStore.remove(region, date);

        if (isDeleted) {
            Colour.printlnRandomColor("Weather Forecast Deleted Successfully... ");
        } else {
            Colour.printlnRandomColor("Delete NOT Successful... ");
        }
    }

    private void updateTemperature() {
        input.nextLine(); // Bug fix
        Colour.printlnRandomColor("Enter Region: ");
        String region = input.nextLine();
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd): ");
        String date = input.nextLine();
        Colour.printlnRandomColor("Enter New Temperature: ");
        int temperature = input.nextInt();

        boolean isChanged = weatherStore.updateTemperature(temperature, region, date);

        if (isChanged) {
            Colour.printlnRandomColor("Update Successful... ");
        } else {
            Colour.printlnRandomColor("Update NOT Successful... ");
        }
    }

    private void updateCondition() {
        input.nextLine(); // Bug fix
        Colour.printlnRandomColor("Enter Region: ");
        String region = input.nextLine();
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd): ");
        String date = input.nextLine();
        Colour.printlnRandomColor("Enter New Condition (Sunny or Rainy): ");
        String condition = input.nextLine();

        boolean isChanged = weatherStore.updateCondition(condition, region, date);

        if (isChanged) {
            Colour.printlnRandomColor("Update Successful... ");
        } else {
            Colour.printlnRandomColor("Update NOT Successful... ");
        }
    }

    private void getAdvice() {
        Colour.printlnRandomColor("///////////////////////////////////////");
        Colour.printlnRandomColor("Get Behavioral Advice");
        Colour.printlnRandomColor("///////////////////////////////////////");
        input.nextLine(); // Required for bug in Scanner Class
        Colour.printlnRandomColor("Enter Region to Search for =>>");
        String region = input.nextLine();
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd) to Search for =>>");
        String date = input.nextLine();

        String advice = weatherStore.getAdvice(region, date);
        Colour.printlnRandomColor(String.valueOf(advice));
    }

    private void findWeatherForecast() {
        Colour.printlnRandomColor("///////////////////////////////////////");
        Colour.printlnRandomColor("Search Weather Forecast");
        Colour.printlnRandomColor("///////////////////////////////////////");
        input.nextLine(); // Required for bug in Scanner Class
        Colour.printlnRandomColor("Enter Region to Search for =>>");
        String region = input.nextLine();
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd) to Search for =>>");
        String date = input.nextLine();

        WeatherDay foundWeather = weatherStore.find(region, date);

        if (foundWeather != null) {
            Colour.printlnRandomColor(String.valueOf(foundWeather));
        } else {
            Colour.printlnRandomColor("No Weather Forecast found for Region: " + region + " and Date: " + date);
        }
    }

    private void addWeatherForecast() {
        Colour.printlnRandomColor("Please Enter Weather Forecast Details... ");

        input.nextLine(); // Required for bug in Scanner Class
        Colour.printlnRandomColor("Enter Region: ");
        String region = input.nextLine();
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd): ");
        String date = input.nextLine();

        // Validate date format and actual date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = null;
        while (parsedDate == null) {
            try {
                parsedDate = LocalDate.parse(date, dateFormatter);
            } catch (DateTimeParseException e) {
                Colour.printlnRandomColor("Error: Invalid Date Format. Please enter the date in yyyy-MM-dd format: ");
                date = input.nextLine();
                continue; // Skip further checks if format is incorrect
            }

            // Check if the parsed date is valid
            if (parsedDate.isBefore(LocalDate.MIN) || parsedDate.isAfter(LocalDate.MAX)) {
                Colour.printlnRandomColor("Error: Date is out of range. Please enter a valid date: ");
                date = input.nextLine();
                parsedDate = null; // Reset parsedDate to null to re-enter the loop
            }
        }

        // Validate day name
        String dayName = parsedDate.getDayOfWeek().toString();
        String userInputDayName = "";
        while (!dayName.equalsIgnoreCase(userInputDayName)) {
            Colour.printlnRandomColor("Enter Day Name(From Monday to Sunday): ");
            userInputDayName = input.nextLine();

            if (!dayName.equalsIgnoreCase(userInputDayName)) {
                // Modify the error message to capitalize the first letter of the day name
                String capitalizedDayName = dayName.substring(0, 1).toUpperCase() + dayName.substring(1).toLowerCase();
                Colour.printlnRandomColor("Error: The day name does not match the date. Please enter a valid day (" + capitalizedDayName + "): ");
            }
        }

        Colour.printlnRandomColor("Enter Weather Condition(Sunny or Rainy): ");
        String weatherCondition = input.nextLine();

        // Validate weather condition
        while (!weatherCondition.equals("Sunny") && !weatherCondition.equals("Rainy")) {
            Colour.printlnRandomColor("Error: Invalid Weather Condition. Please enter 'Sunny' or 'Rainy': ");
            weatherCondition = input.nextLine();
        }

        // Validate temperature
        Colour.printlnRandomColor("Enter Temperature (°C): ");
        int temperature = input.nextInt();
        input.nextLine(); // Extra read for bug in Scanner Class

        while (temperature < -50 || temperature > 50) {
            Colour.printlnRandomColor("Error: Invalid Weather Temperature. Please enter a valid temperature between -50°C and 50°C: ");
            temperature = input.nextInt();
            input.nextLine(); // Extra read for bug in Scanner Class
        }

        Colour.printlnRandomColor("Enter Weather Description: ");
        String weatherDescription = input.nextLine();

        WeatherDay newWeatherDay = new WeatherDay(region, date, weatherCondition, temperature, dayName, weatherDescription);
        // Check if the weather forecast already exists
        WeatherDay existingWeatherDay = weatherStore.find(region, date);
        if (existingWeatherDay != null && existingWeatherDay.getRegion().equals(region) && existingWeatherDay.getDate().equals(date)) {
            Colour.printlnRandomColor("Error: Weather Forecast Already Exists... ");
        } else {
            boolean isAdded = weatherStore.add(newWeatherDay);
            if (isAdded) {
                Colour.printlnRandomColor("Weather Forecast Added Successfully... ");
            } else {
                Colour.printlnRandomColor("No Weather Forecast Added... ");
            }
        }
    }

    private void printWeatherForecasts() {
        Colour.printlnRandomColor("");
        Colour.printlnRandomColor("///////////////////////");
        Colour.printlnRandomColor("////Weather Forecasts////");
        Colour.printlnRandomColor("///////////////////////");
        Colour.printlnRandomColor("");
        Colour.printlnRandomColor(String.valueOf(weatherStore.getListWeather()));
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public void setup() {
        Colour.printlnRandomColor("///////////////////////////////////////");
        Colour.printlnRandomColor("Weather Forecast App V1.0.");
        Colour.printlnRandomColor("Developed by: " + "FanXinkang and WangShuo.");
        Colour.printlnRandomColor("The program starts at: " + getLocalDateTime());
        Colour.printlnRandomColor("///////////////////////////////////////");
        Colour.printlnRandomColor("");
        Colour.printlnRandomColor("");
        Colour.printlnRandomColor("Welcome to our Weather Forecast App V1.0.");
        Colour.printRandomColor("Please wait while the system loads");
        try {
            Colour.printRandomColor("...");
            TimeUnit.SECONDS.sleep(1);
            Colour.printRandomColor("...");
            TimeUnit.SECONDS.sleep(1);
            Colour.printRandomColor("...");
            TimeUnit.SECONDS.sleep(1);
            Colour.printRandomColor("...");
            Colour.printRandomColor("");
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }


    private void listWeatherForecastsByRegion() {
        input.nextLine(); // Required for bug in Scanner Class
        Colour.printlnRandomColor("Enter Region to List Weather Forecasts: ");
        String region = input.nextLine();

        List<WeatherDay> forecasts = weatherStore.listByRegion(region);

        if (forecasts.isEmpty()) {
            Colour.printlnRandomColor("No Weather Forecasts found for Region: " + region);
        } else {
            Colour.printlnRandomColor("Weather Forecasts for Region: " + region);
            for (WeatherDay forecast : forecasts) {
                Colour.printlnRandomColor(String.valueOf(forecast));
            }
        }
    }

    private void listWeatherForecastsByDate() {
        input.nextLine(); // Required for bug in Scanner Class
        Colour.printlnRandomColor("Enter Date (yyyy-MM-dd) to List Weather Forecasts: ");
        String date = input.nextLine();

        List<WeatherDay> forecasts = weatherStore.listByDate(date);

        if (forecasts.isEmpty()) {
            Colour.printlnRandomColor("No Weather Forecasts found for Date: " + date);
        } else {
            Colour.printlnRandomColor("Weather Forecasts for Date: " + date);
            for (WeatherDay forecast : forecasts) {
                Colour.printlnRandomColor(String.valueOf(forecast));
            }
        }
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public void dummyData() {
        WeatherDay w1 = new WeatherDay("Nanjing",
                "2024-12-17",
                "Sunny",
                5,
                "Tuesday",
                "Wow! The day is so nice!");
        weatherStore.add(w1);

        WeatherDay w2 = new WeatherDay("Beijing",
                "2024-12-18",
                "Rainy",
                3,
                "Wednesday",
                "Oh! It feels like it's raining cats and dogs!");
        weatherStore.add(w2);
    }
}
// End of WeatherApp Class