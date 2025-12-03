public class HierarchialInheritance {
    public static void main(String args[]) {
        Animal animal = new Animal();
        animal.breathe();
        animal.walk();
        
        Human human = new Human();
        human.breathe();
        human.think();
    }
}

//Both Animal and Human inherits the properties of Living Being. So it is hierarchial inheritance.
class LivingBeing {
    void breathe() {
        System.out.print("breathing...");
    }
}

class Animal extends LivingBeing {
    void walk() {
        System.out.print("walking...");
    }
}

class Human extends LivingBeing {
    void think() {
        System.out.println("thinking...");
    }
}