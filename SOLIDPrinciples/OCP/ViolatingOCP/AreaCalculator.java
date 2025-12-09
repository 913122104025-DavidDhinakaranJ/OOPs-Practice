class AreaCalculator {
    public double calculateArea(Object shape) {
        //Here we have to modify the existing code to add new shape. So it violates OCP.
        switch (shape) {
            case Square s -> {
                return s.side * s.side;
            }
            case Rectangle r -> {
                return r.length * r.width;
            }
            default -> {
                return 0;
            }
        }
    }
}