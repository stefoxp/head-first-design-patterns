package HomeTheater;

class TheaterLights {
    String name = "Theater Ceiling Lights";

    void dim(int i) {
        System.out.println(name + " dimming to " + Integer.toString(i) + "%");
    }

    void on() {
        System.out.println(name + " on");
    }
    
}
