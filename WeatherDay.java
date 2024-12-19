public class WeatherDay {
    private String region;
    private String date;
    private String weatherCondition;
    private int temperature;
    private String dayName;
    private String weatherDescription;

    public WeatherDay(String region, String date, String weatherCondition, int temperature, String dayName, String weatherDescription) {
        this.region = region;
        this.date = date;
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
        this.dayName = dayName;
        this.weatherDescription = weatherDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCondition() {
        return weatherCondition;
    }

    public void setCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    @Override
    public String toString() {
        return "Region: " + region + ", Date: " + date + ", Condition: " + weatherCondition + ", Temperature: " + temperature + "°C, Day Name: " + dayName + ", WeatherDescription: " + weatherDescription;
    }
}
// End of WeatherDay Class
