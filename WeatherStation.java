/*SLIP 5: 
 * Write a JAVA Program to implement built-in support (java.util.Observable) 
 * Weather station with members temperature, humidity, pressure and methods mesurmentsChanged(), 
 * setMesurment(), getTemperature(), getHumidity(), getPressure()
 */
import java.util.Observable;
// WeatherData class that acts as the subject
class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    // Constructor
    public WeatherData() {
        // Initialization code, if needed
    }

    // Method to notify observers that measurements have changed
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    // Method to set measurements and notify observers
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    // Getter methods for temperature, humidity, and pressure
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

// Observer class that acts as a subscriber
class WeatherObserver implements java.util.Observer {
    // Update method to be called when the observed object is changed
    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) observable;
            display(weatherData);
        }
    }

    // Display method to show weather information
    private void display(WeatherData weatherData) {
        System.out.println("Temperature: " + weatherData.getTemperature() + "°C");
        System.out.println("Humidity: " + weatherData.getHumidity() + "%");
        System.out.println("Pressure: " + weatherData.getPressure() + " hPa");
        System.out.println("---------------------------");
    }
}

public class WeatherStation {
    public static void main(String[] args) {
        // Create a weather data object
        WeatherData weatherData = new WeatherData();

        // Create an observer
        WeatherObserver observer = new WeatherObserver();

        // Add the observer to the weather data
        weatherData.addObserver(observer);

        // Simulate weather changes
        weatherData.setMeasurements(25.5f, 60.2f, 1013.5f);
        weatherData.setMeasurements(28.0f, 55.8f, 1010.0f);
    }
}
