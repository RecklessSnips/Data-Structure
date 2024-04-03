package Array;

public class Reverse_Array {

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4, 5};
        int[] test1 = new int[]{1, 2, 3, 4, 5, 6};
        Reverse_Array reverse_array = new Reverse_Array();
        reverse_array.reverse(test);
        System.out.println();
        reverse_array.reverse(test1);
    }

    public void reverse(int[] array){
        int start = 0;
        int end = array.length - 1;
        while (start < end){
            int tmp = 0;
            tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            start++;
            end--;
        }
        show(array);
    }

    public static void show(int[] array){
        for (int j : array) {
            System.out.println(j);
        }
    }
}
