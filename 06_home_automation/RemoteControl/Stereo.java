public class Stereo {
    private String location;

    Stereo(String location) {
        this.location = location;
    }
    
    public void on() {
        System.out.println(this.location + " stereo is on");
    }
    
    public void off() {
        System.out.println(this.location + " stereo is off");
    }
    
    public void setCd() {}
    public void setDvd() {}
    public void setRadio() {}
    public void setVolume(int level) {}
}
