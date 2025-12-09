class TV implements Switchable {
    
    @Override
    public void switchOn() {
        System.out.println("TV is on");
    }
    
    @Override
    public void switchOff() {
        System.out.println("TV is off");
    }
}