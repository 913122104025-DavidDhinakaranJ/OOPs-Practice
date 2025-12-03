public class Association {
    public static void main(String args[]) {
        Food salad = new Food("Salad");
        Person he = new Person("He");
        
        he.eats(salad);
    }
}

class Food {
    String name;
    Food(String name) {
        this.name = name;
    }
}

class Person {
    String name;
    Person(String name) {
        this.name = name;
    }
    
    //Here two independent classes interacts with each other - Association
    void eats(Food food) {
        System.out.println(this.name + " eats " + food.name);
    }
}