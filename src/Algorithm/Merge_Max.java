package Algorithm;

import java.util.Arrays;

public class Merge_Max {

    public static int findMax(int[] array){
        if (array.length < 2){
            return array[0];
        }
        int mid = array.length/2;
        // initialize left right array
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        // copy them using pointer
        int rp = 0;
        for (int i = 0; i < array.length; i++) {
            if (i < mid){
                left[i] = array[i];
            }else{
                right[rp] = array[i];
                rp++;
            }
        }
        int l = findMax(left);
        int r = findMax(right);
        return conquer(l, r);
    }

    public static int conquer(int left, int right){
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] test = new int[]{99, 2, -1, 4, 5};
        System.out.print(findMax(test));
    }
}
