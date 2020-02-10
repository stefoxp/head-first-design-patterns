// using the command object
public class SimpleRemoteControl {
    // we have one slot to hold our command, which will control one device
    Command slot;

    public SimpleRemoteControl() {}

    // we have a method for setting the command the slot is going to control. This could be called multiple times if the client of this code wanted to change the behavior of the remote button
    public void setCommand(Command command) {
        slot = command;
    }

    // this method is called when the button is pressed. All we do is take the current commmand bound to the slot and call its execute() method
    public void buttonWasPressed() {
        slot.execute();
    }
}