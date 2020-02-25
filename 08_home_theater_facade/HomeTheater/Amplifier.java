package HomeTheater;

class Amplifier {
    String name = "Top-O-Line Amplifier";
    
    void on() {
        System.out.println(name + " on");
    }

    void setDvd(DvdPlayer dvd) {
        System.out.println(name + " setting DVD player to Top-O-Line DVD Player");
    }

    void setSurroundSound() {
        System.out.println(name + " surround sound on (5 speackers, 1 subwoofer");
    }

    void setVolume(int i) {
        System.out.println(name + " setting volume to 5");
    }

    void off() {
        System.out.println(name + " off");
    }
    
}
