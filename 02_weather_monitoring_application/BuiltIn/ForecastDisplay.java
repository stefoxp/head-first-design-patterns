package BuiltIn;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author stefano
 */
public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;
    
    public ForecastDisplay(Observable observable) {
        observable.addObserver(this);
    }

    public void update(Observable observable, Object arg) {
        if(observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)observable;
            this.lastPressure = currentPressure;
            currentPressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Forecast: bhò");
    }
    
}
