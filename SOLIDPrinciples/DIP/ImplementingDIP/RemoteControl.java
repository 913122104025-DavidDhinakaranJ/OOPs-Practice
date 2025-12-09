class RemoteControl {
    //Here the High level module(Remote Control) depends on abstraction(Switchable). So, it satisfies DIP.
    Switchable device;
    
    public RemoteControl(Switchable device) {
        this.device = device;
    }
    
    public void turnOn() {
        device.switchOn();
    }
    
    public void turnOff() {
        device.switchOff();
    }
}