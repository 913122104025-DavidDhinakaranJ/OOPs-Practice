public class ViolatingOCP {
    public static void main(String args[]) {
        AreaCalculator areaCalculator = new AreaCalculator();
        
        Square square = new Square(10);
        Rectangle rectangle = new Rectangle(10, 20);
        
        System.out.println("Area of Square: " + areaCalculator.calculateArea(square));
        System.out.println("Area of Rectangle: " + areaCalculator.calculateArea(rectangle));
    }
}