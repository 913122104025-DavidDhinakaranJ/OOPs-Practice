public class Typecasting {
    public static void main(String args[]) {
        Parent p = new Child();     //Upcasting
        p.display();
        //p.displayMessage();     //We cannot access method in child class using Parent variable. So we need to Downcast.
        
        Child c = (Child) p;
        c.displayMessage();
    }
}

class Parent {
    void display() {
        System.out.println("Parent Class.");
    }
}

class Child extends Parent {
    void displayMessage() {
        System.out.println("Hello from Child Class");
    }
    @Override
    void display() {
        System.out.println("Child Class.");
    }
}