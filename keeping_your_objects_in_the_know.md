# 2 Keeping your Objects in the know

>Observer Pattern

## Sample: Weather Monitoring application

To create an app that uses the WeatherData object to update three displays for current conditions, weather stats and a forecast.

file WeatherData.java:

```java
class WeatherData {
    public float getTemperature() {}
    public float getHumidity() {}
    public float getPressure() {}

    public void measurementsChanged() {
        // Your code goes here
    }
}
```

- The measurementsChanged() method is called any time new weather measurement data is available.
- we need implement three display elements that use the weather data. These displays must be updated each time WeatherData has new measurements.
- the system must be expandable.

### Weather Monitoring application: First implementation

```java
class WeatherData {
    float getTemperature() {}
    float getHumidity() {}
    float getPressure() {}

    public void measurementsChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();

        currentConditionsDisplay.update(temp, humidity, pressure);
        statisticsDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
    }

    // other WeatherData methods here
}
```

What's wrong with our implementation?

- we need to encapsulate the area of change
- coding to concrete implementations is bad
- update() method takes the same values

### the Observer Pattern

Publishers + Subscribers = Observer Pattern

where

- Publishers = Subject

and

- Subscribers = Observers

>the Observer Pattern defines a one-to-many relationship between a set of objects. When the state of one object (the subject) changes, all of its dependent objects (the observers) are notified.

#### Observer Pattern: the class diagram

```java
interface Subject {
    registerObserver()
    removeObserver()
    notifyObservers()
}

interface Observer {
    update()
}

class ConcreteSubject implements Subject {
    registerObserver() {}
    removeObserver() {}
    notifyObservers() {}

    getState()
    setState()
}

class ConcreteObserver implements Observer {
    update() {}
    // other Observer specific methods
}

```

The Observer Pattern provides an object design where subjects and observers are **loosely coupled** (they can interact, but have very little knowledge of each other):

- the only thing the subject knows about an observer is that it implements a certain interface
- we can add new observers at any time
- we never need to modify the subject to add new types of observers
- we can reuse subjects or observers independently of each other
- changes to either the subject or an observer will not affect the other

>Design Principle (a.4)

Loosely coupled designs allow us to build flexible OO systems that can handle change because they minimize the interdependency between objects.

### Weather Monitoring application: the second implementation

```java
// Subject.java
interface Subject {
    void registerObserver()
    void removeObserver()
    void notifyObservers()
}

// WeatherData.java
class WeatherData implements Subject {
    void registerObserver() {}
    void removeObserver() {}
    void notifyObservers() {}

    float getTemperature() {}
    float getHumidity() {}
    float getPressure() {}

    public void measurementsChanged() {}

    // other WeatherData methods here
}

interface Observer {
    void update()
}

interface DisplayElement {
    void display()
}

class CurrentConditionsDisplay implements Observer, DisplayElement {
    void update() {}
    void display() {
        // display current measurements
    }
}

class StatisticsDisplay implements Observer, DisplayElement {
    void update() {}
    void display() {
        // display the average, min and max measurements
    }
}

class ThirdPartyDisplay implements Observer, DisplayElement {
    void update() {}
    void display() {
        // display something else based on measurements
    }
}

class ForecastDisplay implements Observer, DisplayElement {
    void update() {}
    void display() {
        // display the forecast
    }
}
```

- Implementation Code [Weather Monitoring application 02](02_weather_monitoring_application)

### Weather Monitoring application: implementation with Java's built-in Observer Pattern

```java
class Observable {
    addObserver()
    deleteObserver()
    notifyObservers()
    setChanged()
}

interface Observer {
    update()
}

class WeatherData extends Observable {
    getTemperature()
    getHumidity()
    getPressure()
}

class GeneralDisplay implements Observer {
    update()
    display()
}

class StatisticsDisplay implements Observer {
    update()
    display()
}

class ForecastDisplay implements Observer {
    update()
    display()
}
```

The java.util.Observable implementation has a number of problems that limit its usefulness and reuse:

- Observable is a class (not an interface -> violate a first OO Design Principle)
- Observable protects crucial methods (-> violates a second OO Design Principle)
