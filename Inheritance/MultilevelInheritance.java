public class MultilevelInheritance {
    public static void main(String args[]) {
        Dog dog = new Dog();
        
        //Here Dog inherits breathe() from Animal which inherits it from Living Being.
        dog.breathe();
        
        dog.walk();
        dog.eat();
        
        dog.bark();
    }
}

class LivingBeing {
    void breathe() {
        System.out.print("breathing...");
    }
}

class Animal extends LivingBeing {
    void walk() {
        System.out.print("walking...");
    }
    
    void eat() {
        System.out.print("eating...");
    }
}

//LivingBeing -> Animal -> Dog
class Dog extends Animal {
    void bark() {
        System.out.print("barking...");
    }
}