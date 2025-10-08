package Lecture12;

public abstract class Shape {
    private int x;
    private int y;

    public Shape() {
       System.out.println("Shape constructor");
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;

    }
    @Override
    public String toString() {
        return "InheritanceTest";
    }
    public final void display() {
        System.out.println("ShapeTest");
    }
    public abstract void draw();

    public static void main(String[] args) {
        Shape s;

//        System.out.println((new Sphere(5, 10)));
        Circle.Sphere s1 = new Circle.Sphere(5, 10);
    }

}
class Rectangle extends Shape
{
    int width;
    int height;
    public Rectangle(int width, int height) {
        System.out.println("Rectangle constructor");
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

}

class Circle extends Shape {
    int radius;

    public Circle(int radius) {
        System.out.println("Circle constructor");
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
    }

    static class Sphere extends Circle {
        int z;

        public Sphere(int z, int radius) {
            super(radius);
            this.z = z;
            System.out.println("Sphere constructor");
        }

        @Override
        public String toString() {
            return String.format("%d, %d, %d, %d", getX(), getY(), getRadius(), z);
        }

    }

}
