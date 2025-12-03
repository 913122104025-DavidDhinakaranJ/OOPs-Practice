public class Encapsulation {
    public static void main(String args[]) {
        LaptopBag bag = new LaptopBag();
        
        Laptop laptop = bag.takeLaptop();
        Charger charger = bag.takeCharger();
        Mouse mouse = bag.takeMouse();
        
        bag.keepLaptop(laptop);
        bag.keepCharger(charger);
        bag.keepMouse(mouse);
    }
}

class LaptopBag {
    private Laptop laptop = new Laptop();
    private Charger charger = new Charger();
    private Mouse mouse = new Mouse();

    public Laptop takeLaptop() {
        Laptop item = laptop;
        laptop = null;
        return item;
    }

    public void keepLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Charger takeCharger() {
        Charger item = charger;
        charger = null;
        return item;
    }

    public void keepCharger(Charger charger) {
        this.charger = charger;
    }

    public Mouse takeMouse() {
        Mouse item = mouse;
        mouse = null;
        return item;
    }

    public void keepMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}

class Laptop {}

class Charger {}

class Mouse {}