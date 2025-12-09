public class ImplementingDIP {
    public static void main(String args[]) {
        Switchable tv = new TV();
        Switchable ac = new AC();
        
        RemoteControl tvRemote = new RemoteControl(tv);
        RemoteControl acRemote = new RemoteControl(ac);
        
        tvRemote.turnOn();
        tvRemote.turnOff();
        
        acRemote.turnOn();
        acRemote.turnOff();
    }
}