public class MultipleInheritanceConflict {
    public static void main(String args[]) {
        D d = new D();
        d.display();
    }
}


//Here it shows error because both the interfaces A and B has its own implementation of display(). So we must override that method. 
/*class D implements A, B {
    
}*/

//Here the display() in class C overrides the display() in A and B, so no conflict
class D extends C implements A, B {
    
}

interface A {
    default void display() {
        System.out.println("A");
    }
}

interface B {
    default void display() {
        System.out.println("B");
    }
}

abstract class C {
    public void display() {
        System.out.println("B");
    }
}