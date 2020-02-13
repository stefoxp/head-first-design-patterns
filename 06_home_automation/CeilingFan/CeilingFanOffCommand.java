class CeilingFanOffCommand implements Command {
    CeilingFan ceilingFan;
    // we've added local state to keep track of the previous speed of the fan
    int prevSpeed;
    
    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }
    
    @Override
    public void execute() {
        // before we change the speed of the fan, we need to first record its previous state
        // just in case we need to undo our actions
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
    }

    @Override
    public void undo() {
        // we set the speed of the fan back to its previous speed
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}
