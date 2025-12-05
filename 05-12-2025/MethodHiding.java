public class MethodHiding {
    public static void main(String args[]) {
        Parent p = new Child();
        Child c = new Child();
        
        //Here the method runs based on the class reference, not on the object.
        //This is because the static methods are resolved at compile time.
        p.display();
        c.display();
    }
}

class Parent {
    static void display() {
        System.out.println("Parent Class.");
    }
}

class Child extends Parent {
    static void display() {
        System.out.println("Child Class.");
    }
}