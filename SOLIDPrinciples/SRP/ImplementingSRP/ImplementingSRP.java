public class ImplementingSRP {
    public static void main(String args[]) {
        //Here each of the worker has a single responsiblity.
        Chef worker1 = new Chef();
        Waiter worker2 = new Waiter();
        Cleaner worker3 = new Cleaner();
        
        worker1.cookFood();
        worker2.serveFood();
        worker3.cleanPlates();
    }
}