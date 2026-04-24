package part4.part4_2;

public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double area();
    public abstract double perimeter();
    public abstract void draw();

    public int compareArea(Shape other) {
        return Double.compare(this.area(), other.area());
    }

    @Override
    public String toString() {
        return String.format("%s[цвет=%s, S=%.2f, P=%.2f]",
            getClass().getSimpleName(), color, area(), perimeter());
    }
}
