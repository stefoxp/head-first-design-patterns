/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuiltIn;

/**
 *
 * @author stefano
 */
public class WeatherStationBi {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        // StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        
        weatherData.setMeasurements(85, 65, 30.4f);
        weatherData.setMeasurements(90, 70, 29.2f);
        weatherData.setMeasurements(70, 90, 29.2f);
    }
}
