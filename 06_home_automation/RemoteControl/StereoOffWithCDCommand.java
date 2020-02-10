public class StereoOffWithCDCommand implements Command {
    Stereo stereo;
    
    public StereoOffWithCDCommand(Stereo stereo) {
        // like the LightOffCommand, we get passed the instance of the stereo
        // we are going to be controlling and we store it in a local instance variable
        this.stereo = stereo;
    }
    
    @Override
    public void execute() {
        stereo.off();
    }
}
