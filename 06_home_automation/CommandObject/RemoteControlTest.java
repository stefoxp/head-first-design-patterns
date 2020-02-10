// this is our Client in Command Pattern-speak
public class RemoteControlTest {
    public static void main(String[] args) {
        // the remote is our Invoker; it will be passed a command
        // object that can be used to make requests
        SimpleRemoteControl remote = new SimpleRemoteControl();
        // now we create a Light object, this will be the Receiver of the request
        Light light = new Light();
        // create a command and pass it to the Receiver
        LightOnCommand lightOn = new LightOnCommand(light);
        
        // pass the command to the Invoker
        remote.setCommand(lightOn);
        // we simulate the button being pressed
        remote.buttonWasPressed();
    }
}
