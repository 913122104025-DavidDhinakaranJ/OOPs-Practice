class RemoteControl {
    //Here the High level module(Remote Control) directly depends on Low level module(TV, AC). So, it violates DIP.
    private TV tv;
    private AC ac;
    
    public RemoteControl() {
        tv = new TV();
        ac = new AC();
    }
    
    public void switchOnTV() {
        tv.switchOn();
    }
    
    public void switchOffTV() {
        tv.switchOff();
    }
    
    public void switchOnAC() {
        ac.switchOn();
    }
    
    public void switchOffAC() {
        ac.switchOff();
    }
}