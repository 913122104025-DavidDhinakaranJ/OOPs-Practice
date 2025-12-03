public class Abstraction {
    public static void main(String args[]) {
        WashingMachine washer = new BrandedWashingMachine();
        
        washer.selectMode("Quick Wash");
        washer.start();
        washer.stop();
    }
}

interface WashingMachine {
    void start();
    void stop();
    void selectMode(String mode);
}

class BrandedWashingMachine implements WashingMachine{
    private String mode = "Default";
    
    @Override
    public void start() {
        System.out.println("Washing Machine Started.");
        //The below operations are done based on mode selected.
        fillWater();
        rotateDrum();
        drainWater();
    }

    @Override
    public void stop() {
        System.out.println("Washing Machine Stopped.");
        //logic for stopping washing machine.
    }

    @Override
    public void selectMode(String mode) {
        this.mode = mode;
        System.out.println(mode + " is selected.");
    }
    
    //These complex implementations are hidden with abstraction.
    private void fillWater() {
        //logic for filling water in a washing machine.
    }
    
    private void rotateDrum() {
        //logic for rotating drum in washing machine.
    }
    
    private void drainWater() {
        //logic for draining water from washing machine.
    }
}