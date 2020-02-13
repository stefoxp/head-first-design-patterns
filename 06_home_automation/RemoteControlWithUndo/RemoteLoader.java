public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
        
        // create all the devices in their proper locations
        Light livingRoomLight = new Light("Living Room");
        
        // create all the Light Command objects
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        
        // now that we've got all our commands, we can load them into the remote slots
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);

        // turn the light on, then off and then undo
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();
        // turn the light off, back on and undo
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();
    }
}
