package Lecture8;

import java.util.Arrays;

public class DeepShallow {
    int [][] data;

    public DeepShallow(int [][] data) {
        this.data = data;
    }

    // returns deep copy of 'data'
    public int[][] getData() {
        int[][] data = new int[this.data.length][];
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {
                data[i] = new int[this.data[i].length];
                data[i][j] = this.data[i][j];
            }
        }
        return data;
    }

    // returns shallow copy of 'data'
    public int[][] getDataUnsafe()  {
        return data;
    }
    public static void main(String[] args) {
        int[][]data = new int[2][3];
        data[0][0] = 1;

        data[0][1] = 2;
        DeepShallow ds = new DeepShallow(data);

        int [][] data_deepcopy = ds.getData(); // get a deep copy
        data_deepcopy[0][0] = 100; // change/mutate data element
        data_deepcopy[0][1] = 200;

        System.out.println("updated data_deepcopy (deep copy) values:");
        System.out.println(data_deepcopy[0][0]);
        System.out.println(data_deepcopy[0][1]);

        System.out.println("original data values:");
        System.out.println(data[0][0]);
        System.out.println(data[0][1]);


        int [][] data_shallowcopy = ds.getDataUnsafe();
        data_shallowcopy[0][0] = 300;
        data_shallowcopy[0][1] = 400;

        System.out.println("updated data_shallowcopy values:");
        System.out.println(data_shallowcopy[0][0]);
        System.out.println(data_shallowcopy[0][1]);

        System.out.println("original data values:");
        System.out.println(data[0][0]);
        System.out.println(data[0][1]);
    }
}
class DeepShallowTest {

}
