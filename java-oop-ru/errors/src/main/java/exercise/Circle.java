package exercise;

// BEGIN
class Circle {
    Point center;
    private final int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
           throw new NegativeRadiusException("Радиус не может быть отрицательным");
        }
        return Math.PI * (Math.pow(radius,2));
        }
}
// END
