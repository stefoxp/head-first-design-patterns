public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        
        // create all the devices in their proper locations
        Light livingRoomLight = new Light("Living Room");
        Light kitchenRoomLight = new Light("Kitchen");
        // ...
        Stereo stereo = new Stereo("Living Room");
        
        // create all the Light Command objects
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenRoomLightOn = new LightOnCommand(kitchenRoomLight);
        LightOffCommand kitchenRoomLightOff = new LightOffCommand(kitchenRoomLight);
        //...
        // create the stereo On and Off commands
        StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        StereoOffWithCDCommand stereoOff = new StereoOffWithCDCommand(stereo);
        
        // now that we've got all our commands, we can load them into the remote slots
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenRoomLightOn, kitchenRoomLightOff);
        //...
        remoteControl.setCommand(3, stereoOnWithCD, stereoOff);
        
        // print each remote slot and the command that it is assigned to
        System.out.println(remoteControl);
        
        // we are ready to roll. Now, we step through each slot
        // and push its On and Off button
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        //...
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
        
        
    }
}
