package HomeTheater;

class DvdPlayer {
    String name = "Top-O-Line DVD Player";
    String movie_on_air = "";
    
    void on() {
        System.out.println(name + " on");
    }

    void play(String movie) {
        System.out.println(name + " playing " + movie);
        this.movie_on_air = movie;
    }

    void stop() {
        System.out.println(name + " stopped " + this.movie_on_air);
        this.movie_on_air = "";
    }

    void eject() {
        System.out.println(name + " eject");
    }

    void off() {
        System.out.println(name + " off");
    }
    
}
