import java.util.ArrayList;

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

    public boolean delete(String region, String date) {
        WeatherDay w = find(region, date);
        if (w != null) {
            return weatherList.remove(w);
        }
        return false;
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
}