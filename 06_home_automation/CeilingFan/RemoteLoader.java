public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
        
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        
        // we instantiate three commands
        CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);
        
        // we put medium in slot zero
        remoteControl.setCommand(0, ceilingFanMedium, ceilingFanOff);
        // we put high in slot one
        remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff);
        
        // turn the fan on medium
        remoteControl.onButtonWasPushed(0);
        // then turn it off
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        // undo. It should go back to medium
        remoteControl.undoButtonWasPushed();
        
        // turn on to high
        remoteControl.onButtonWasPushed(1);
        System.out.println(remoteControl);
        // undo
        remoteControl.undoButtonWasPushed();
    }
}
