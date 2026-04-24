package part4.part4_2;

public class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть > 0");
        this.radius = radius;
    }

    @Override
    public double area() { return Math.PI * radius * radius; }

    @Override
    public double perimeter() { return 2 * Math.PI * radius; }

    @Override
    public void draw() {
        System.out.println(
                "Рисую " + color + " круг с радиусом " + radius);
    }
}
