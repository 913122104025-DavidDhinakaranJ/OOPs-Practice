public class ClassesAndObjects {
    public static void main(String args[]) {
        //Creating objects(cars) using Class(car template)
        Car myCar = new Car();
        
        myCar.color = "blue";
        myCar.model = "BMW";
        myCar.manufacturingYear = 2024;
        
        myCar.start();
        myCar.accelerate();
        myCar.applyBrake();
    }
}

//Class(Template) for Car
class Car {
    String color;
    String model;
    int manufacturingYear;
    
    void start() {
        //logic for starting a car
        System.out.println("Car Starts.");
    }
    
    void accelerate() {
        //logic for accelerating a car
        System.out.println("Accelerates Car.");
    }
    
    void applyBrake() {
        //logic for stopping a car
        System.out.println("Brake applied to Car.");
    }
}