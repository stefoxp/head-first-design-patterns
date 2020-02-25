package HomeTheater;

class Projector {
    String name = "Top-O-Line Projector";
    
    void on() {
        System.out.println(name + " on");
    }

    void wideScreenMode() {
        System.out.println(name + " in widescreen mode (16x9 aspect ratio)");
    }

    void off() {
        System.out.println(name + " off");
    }
    
}
