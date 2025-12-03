public class MultipleInheritance {
    public static void main(String args[]) {
        FlyingBird sparrow = new FlyingBird();
        
        sparrow.eat();
        sparrow.fly();
    }
}

class Bird {
    void eat() {
        System.out.print("eating...");
    }
}

interface Flyable {
    default void fly() {
        System.out.print("flying...");
    }
}

// Multiple Inheritance - Here Flying Bird inherits both the Bird and the Flyable
class FlyingBird extends Bird implements Flyable {
    
}