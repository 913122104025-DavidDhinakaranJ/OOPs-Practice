public class SingleInheritance {
    public static void main(String args[]) {
        Dog dog = new Dog();
        
        //Here dog inherits walk() and eat() from animal
        dog.walk();
        dog.eat();
        
        dog.bark();
    }
}

class Animal {
    void walk() {
        System.out.print("walking...");
    }
    
    void eat() {
        System.out.print("eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.print("barking...");
    }
}