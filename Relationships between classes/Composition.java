public class Composition {
    public static void main(String args[]) {
        Car car = new Car("Turbo Engine");
        
        System.out.println(car.getEngineType());
    }
}

class Engine {
    String engineType;
    
    Engine(String engineType) {
        this.engineType = engineType;
    }
}

class Car {
    private Engine engine;
    
    //Here Engine is a part of Car (strong ownership). So it is Composition.
    Car(String engineType) {
        this.engine = new Engine(engineType);
    }
    
    String getEngineType() {
        return this.engine.engineType;
    }
}