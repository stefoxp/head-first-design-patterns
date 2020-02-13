public class LightOffCommand implements Command {
    Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    // this class works exactly the same way as the LightOnCommand
    // except that we are binding the receiver to a different action:
    // the off() method
    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
