public class ViolatingLSP {
    public static void main(String args[]) {
        Bird sparrow = new Sparrow();
        sparrow.fly();
        
        //Here Ostrich class does not support fly(). So it cannot be a substitute to Bird class.
        Bird ostrich = new Ostrich();
        ostrich.fly();
    }
}