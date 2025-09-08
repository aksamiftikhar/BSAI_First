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
         if(color_input != null)
             color = color_input;
     }
     public String getColor()
     {
         return color;
     }
}
