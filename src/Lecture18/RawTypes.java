package Lecture18;

import java.util.ArrayList;

public class RawTypes {
    public static void main(String[] args) {
        GenericType<Integer> gt1 = new GenericType<Integer>(1);
        GenericType<Integer> gt2 = new GenericType<>(1);
        GenericType gt3 = new GenericType<Integer>(1);
        GenericType gt4 = new GenericType<>(1);
//        GenericType gt5 = new GenericType("1");

        ArrayList<GenericType> list = new ArrayList<GenericType>();
        list.add(gt1);
        list.add(gt2);
        list.add(gt3);
        list.add(gt4);
//        list.add(gt5);

    }
}

class GenericType<T extends Integer>
{
    T field;
    public GenericType(T field)
    {
        this.field = field;
    }
    public  T getField()
    {
        return field;
    }
    public void  setField(T field)
    {
        this.field = field;
    }
//    public void stepDown()
//    {
//        field;
//    }

}