public class CompileTimePolymorphism {
    public static void main(String args[]) {
        DigitalClock clock = new DigitalClock();
        
        clock.displayTime(14, 30);
        
        clock.displayTime(2, 30, "PM");
    }
}

class DigitalClock {
    void displayTime(int hour, int minute) {
        System.out.println(hour + ":" + minute + " in 24 hour format.");
    }
    
    void displayTime(int hour, int minute, String period) {
        System.out.println(hour + ":" + minute + " " + period + " in 12 hour format.");
    }
}