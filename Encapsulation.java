public class Encapsulation {
    public static void main(String args[]) {
        LaptopBag bag = new LaptopBag();
        
        Object laptop = bag.takeLaptop();
        Object charger = bag.takeCharger();
        Object mouse = bag.takeMouse();
        
        bag.keepLaptop(laptop);
        bag.keepCharger(charger);
        bag.keepMouse(mouse);
    }
}

class LaptopBag {
    private Object laptop = new Object();
    private Object charger = new Object();
    private Object mouse = new Object();

    public Object takeLaptop() {
        Object item = laptop;
        laptop = null;
        return item;
    }

    public void keepLaptop(Object laptop) {
        this.laptop = laptop;
    }

    public Object takeCharger() {
        Object item = charger;
        charger = null;
        return item;
    }

    public void keepCharger(Object charger) {
        this.charger = charger;
    }

    public Object takeMouse() {
        Object item = mouse;
        mouse = null;
        return item;
    }

    public void keepMouse(Object mouse) {
        this.mouse = mouse;
    }
}