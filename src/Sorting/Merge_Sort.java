package Sorting;

import java.util.Arrays;

public class Merge_Sort {

    public static void mergeSort(int[] array){
        // split
        if (array.length < 2)
            return;
        int middle = array.length / 2;
        int[] left = new int[middle];
        int[] right = new int[array.length - middle];
        // to add data into right array
        int j = 0;
        for(int i = 0; i < array.length; i++){
            if (i < middle){
                left[i] = array[i];
            }else{
                right[j] = array[i];
                j++;
            }
        }
        System.out.println("left array: " + Arrays.toString(left));
        System.out.println("right array: " + Arrays.toString(right));
        mergeSort(left);
        mergeSort(right);
        merge(left, right, array);
    }

    private static void merge(int[] left, int[] right, int[] array){
        // 左右array大小不一样，所以当其中一个拿完，另一个直接挪到后面
        // 因为在最底层都是sort好的，到上层append的时候直接比较，未比较的
        // 就是已经排序好的
        int leftSize = left.length;
        int rightSize = right.length;
        int l = 0, r = 0, k = 0;
        // one of them not exceed size
        // left: [3]
        // right: [-1, 2]
        while (l < leftSize && r < rightSize){
            if (left[l] <= right[r]){
                array[k] = left[l];
                l++;
            }else{
                array[k] = right[r];
                r++;
            }
            k++;
        }
        // append all the left
        while (l < leftSize){
            array[k] = left[l];
            l++;
            k++;
        }
        // append the right
        while(r < rightSize){
            array[k] = right[r];
            r++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, -1, 2, -9 , 10, 5, 0};
        mergeSort(test);
        System.out.print("Sorted array: ");
        for(int i: test){
            System.out.print(i + "  ");
        }
    }
}
