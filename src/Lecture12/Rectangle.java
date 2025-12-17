package Lecture12;

public class Rectangle extends Shape
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
