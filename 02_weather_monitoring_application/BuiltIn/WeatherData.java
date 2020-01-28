package BuiltIn;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author stefano
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherData() { }
    
    public void measurementsChanged() {
        setChanged();       // to indicate the state has changed before calling notify...
        notifyObservers();    // we're using the PULL model
    }


    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    
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
