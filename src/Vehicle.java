public class Vehicle {
     String make;
     String model;
     private String color;

     int year;

     public void display()
     {
         System.out.println(make + " " + model + " " + color + " " + year);
     }

     public void setColor(String color_input)
     {
         color = color_input;
     }
     public String getColor()
     {
         return color;
     }
}
