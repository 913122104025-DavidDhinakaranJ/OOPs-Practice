public class RunTimePolymorphism {
    public static void main(String args[]) {
        //Runtime Polymorphism(Method overriding) - here the overridden method is chosen at runtime based on the actual object
        Vehicle vehicle = new Vehicle();
        String vehicleType = "Car";
        
        switch (vehicleType) {
            case "Car" -> vehicle = new Car();
            case "Bike" -> vehicle = new Bike();
        }
        
        vehicle.drive();
    }
}

class Vehicle {
    void drive() {
        System.out.println("Driving Vehicle.");
    }
}

class Bike extends Vehicle {
    @Override
    void drive() {
        System.out.println("Driving Bike.");
    }
}

class Car extends Vehicle {
    @Override
    void drive() {
        System.out.println("Driving Car.");
    }
}