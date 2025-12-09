public class ImplementingLSP {
    public static void main(String args[]) {
        Bird ostrich = new Ostrich();
        //Now Ostrich can act as a substitute to Bird without breaking behavior.
        ostrich.eat();
        
        FlyingBird sparrow = new Sparrow();
        sparrow.eat();
        sparrow.fly();
    }
}