

public class Main {
    public static float computeRoot(float a, float b, float c)
    {
        float disc;
        disc = b * b - (4 * a * c);
        if(disc < 0){
            return -99999;
        }
        else
        {
            float numerator = -b + disc;
            float denominator = 2 * a;
            float root = numerator / denominator;

            return root;
        }
    }
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle();
        v2.year = 2025;
//        v1.color = "red";
        v1.year = 2020;
        v1.make = "Suzuki";

        v1.display();
        v2.display();

//        System.out.println(v1.color);
//        System.out.println(v1.year);
//        float root=0;
//
//        for(int c = 3; c < 3; c++){
//            root = computeRoot(1,2,c);
//        }
//        if(root == -99999){
//            System.out.println("The root is imaginary");
//        }
//        else {
//            System.out.println("The root is equal to " + root + ".");
//        }

        // Strings demo

//        String str = "Hello";
//        String str2 = new String("Hello");
//        String str_clone = str;
//
//        System.out.println("str is : " + str);
//        System.out.println(str.length());
//
//        String output_str = "Chat at index 8 is: " + "Java is fun".charAt(8) + "\n" +
//                "Comparing str with hello: " + str.compareTo("hello") + "\n" +
//                "Comparing hello with Hello: " + "hello".compareTo("Hello") + "\n" +
//                "Comparing str with Hello: " + str.compareTo("Hello") + "\n" +
//                "Concat Hello with hello using concat function: " + str.concat("hello") + "\n" +
//                "Concat Hello with hello using + operator: " + str.concat("hello") + "\n" +
//                "Equality comparison str with 'Hello' using ==: " + (str == str2) + "\n" +
//                "Equality comparison str with 'Hello' using equals: " + str.equals(str2) + "\n" +
//                "Equality comparison str with str_clone using equals: " + str.equals(str_clone) + "\n" +
//                "Equality comparison str with str_clone using equalsIgnoreCase: " + str.equalsIgnoreCase(str_clone) + "\n" +
//                "Finding indexOf 'f' in str: " + "Java is fun".indexOf("f") + "\n" ;
//
//        System.out.println(output_str);


    }
}