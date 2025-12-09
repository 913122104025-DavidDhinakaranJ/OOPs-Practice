class AreaCalculator {
    public double calculateArea(Shape shape) {
        //Here we can extend by adding multiple shape classes that implements Shape interface, so we don't need to modify any code.
        return shape.area();
    }
}