import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherStore {
    private final ArrayList<WeatherDay> weatherList = new ArrayList<>();

    private boolean isEmpty() {
        return weatherList.isEmpty();
    }

    public boolean add(WeatherDay weather) {
        return weatherList.add(weather);
    }

    public String getListWeather() {
        if (isEmpty()) {
            return "No Weather Data Available";
        } else {
            StringBuilder list = new StringBuilder();
            for (int i = 0; i < weatherList.size(); i++) {
                list.append(i).append(": ").append(weatherList.get(i)).append("\n");
            }
            return list.toString();
        }
    }

    public WeatherDay find(String region, String date) {
        for (WeatherDay w : weatherList) {
            if (w.getRegion().equals(region) && w.getDate().equals(date)) {
                return w;
            }
        }
        return null;
    }

    public boolean updateCondition(String newCondition, String region, String date) {
        WeatherDay w = find(region, date);
        if (w != null) {
            w.setCondition(newCondition);
            return true;
        }
        return false;
    }

    public boolean updateTemperature(int newTemperature, String region, String date) {
        WeatherDay w = find(region, date);
        if (w != null) {
            w.setTemperature(newTemperature);
            return true;
        }
        return false;
    }

    public boolean remove(String region, String date) {
        return weatherList.removeIf(weatherDay -> weatherDay.getRegion().equals(region) && weatherDay.getDate().equals(date));
    }

    public String getAdvice(String region, String date) {
        WeatherDay weather = find(region, date);
        if (weather != null) {
            String condition = weather.getCondition();
            switch (condition.toLowerCase()) {
                case "sunny":
                    return "We recommend you to fly kites in Sunshine Park.";
                case "rainy":
                    return "We recommend you to do some reading or meditate at home.";
                default:
                    return "No recommendation.";
            }
        } else {
            return "No weather data available for the given region and date.";
        }
    }

    public List<WeatherDay> findByRegion(String region) {
        List<WeatherDay> filteredList = new ArrayList<>();
        for (WeatherDay w : weatherList) {
            if (w.getRegion().equalsIgnoreCase(region)) {
                filteredList.add(w);
            }
        }
        filteredList.sort(Comparator.comparing(WeatherDay::getDate));
        return filteredList;
    }

    public List<WeatherDay> listByRegion(String region) {
        return findByRegion(region);
    }

    public List<WeatherDay> listByDate(String date) {
        // 实现根据日期获取所有天气记录的逻辑
        // 例如：
        return weatherList.stream()
                .filter(weatherDay -> weatherDay.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
// End of WeatherStore Class