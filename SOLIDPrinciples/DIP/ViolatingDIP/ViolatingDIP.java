public class ViolatingDIP {
    public static void main(String args[]) {
        RemoteControl remote = new RemoteControl();
        
        remote.switchOnTV();
        remote.switchOnAC();
        
        remote.switchOffTV();
        remote.switchOffAC();
    }
}