public class StereoOnWithCDCommand implements Command {
    Stereo stereo;
    
    public StereoOnWithCDCommand(Stereo stereo) {
        // like the LightOnCommand, we get passed the instance of the stereo
        // we are going to be controlling and we store it in a local instance variable
        this.stereo = stereo;
    }
    
    @Override
    public void execute() {
        // to carry out this request, we need to call three methods on the stereo
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }
}
