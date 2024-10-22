import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeatherApp {
    private List<WeatherDay> weekWeather = new ArrayList<>();
    private WeatherDay nextMondayWeather = null;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new WeatherApp().runApp();
    }

    public void runApp() {
        System.out.println("Welcome to the 7-day weather forecast system！");

        // 输入7天的天气
        for (int i = 1; i <= 7; i++) {
            String dayName = getDayName(i);
            System.out.print("Please enter " + dayName + "'s weather（Sunny/Windy/Rainy）：");
            String weather = scanner.nextLine();
            weekWeather.add(new WeatherDay(dayName, weather));
        }

        // 询问是否添加下个星期一的天气记录
        System.out.print("Would you like to add a weather record for next Monday？（Yes/No）：");
        String addNextMonday = scanner.nextLine();
        if ("Yes".equals(addNextMonday)) {
            System.out.print("The next Monday's weather will be？（Sunny/Windy/Rainy）:");
            String nextMondayWeatherInput = scanner.nextLine();
            nextMondayWeather = new WeatherDay("Next Monday", nextMondayWeatherInput);
        }

        // 询问是否修改这一周的天气
        System.out.print("Do you want to modify the weather this week？（Yes/No）：");
        String modifyWeek = scanner.nextLine();
        if ("Yes".equals(modifyWeek)) {
            System.out.print("Modify the weather on what day of the week？（1-7）：");
            int dayToModify = scanner.nextInt();
            scanner.nextLine(); // 读取换行符
            if (dayToModify >= 1 && dayToModify <= 7) {
                System.out.print("Which one do you want to change to(Sunny/Windy/Rainy):？");
                String newWeather = scanner.nextLine();
                WeatherDay originalDay = weekWeather.get(dayToModify - 1);
                if (!originalDay.getWeather().equals(newWeather)) {
                    originalDay.setWeather(newWeather);
                } else {
                    System.out.println("The weather modification is invalid！");
                }
            } else {
                System.out.println("The weather modification is invalid！");
            }
        }

        // 询问是否删除7天内的任意一天的天气记录
        System.out.print("Do you want to delete the weather record of any day within 7 days？（Yes/No）：");
        String deleteDay = scanner.nextLine();
        if ("Yes".equals(deleteDay)) {
            System.out.print("Delete the weather record of what day of the week？（1-7）：");
            int dayToDelete = scanner.nextInt();
            scanner.nextLine(); // 读取换行符
            if (dayToDelete >= 1 && dayToDelete <= 7) {
                WeatherDay dayToDeleteWeather = weekWeather.get(dayToDelete - 1);
                dayToDeleteWeather.setWeather("unknown weather");
            } else {
                System.out.println("Invalid input,please enter a number from 1 to 7！");
            }
        }

        // 输出最终的天气结果
        System.out.println("The final weather result is：");
        for (WeatherDay day : weekWeather) {
            System.out.println(day);
        }
        if (nextMondayWeather != null) {
            System.out.println(nextMondayWeather);
        }

        // 询问是否查询最终某一天的天气
        System.out.print("All the additions and modifications of the weather have been completed.Do you need to check the weather of a certain day？（Yes/No）：");
        String queryWeather = scanner.nextLine();
        if ("Yes".equals(queryWeather)) {
            System.out.print("Want to check the weather on which day of the week or next Monday？");
            String dayToQuery = scanner.nextLine();
            if ("Next Monday".equals(dayToQuery)) {
                if (nextMondayWeather != null) {
                    System.out.println(nextMondayWeather);
                } else {
                    System.out.println("There is no weather record for next Monday！");
                }
            } else {
                int queryDay = parseDayToNumber(dayToQuery);
                if (queryDay != -1 && queryDay <= weekWeather.size()) {
                    System.out.println(weekWeather.get(queryDay - 1));
                } else {
                    System.out.println("Invalid input,please enter a valid number！");
                }
            }
        }

        System.out.println("Thank you for using our weather forecast system！");
    }

    private String getDayName(int day) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return days[day - 1];
    }

    private int parseDayToNumber(String day) {
        switch (day) {
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            case "Sunday":
                return 7;
            default:
                return -1;
        }
    }
}