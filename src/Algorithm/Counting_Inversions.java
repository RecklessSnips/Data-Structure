package Algorithm;

import java.util.Arrays;

public class Counting_Inversions {

    public static int countInversion(int[] array){
        int length = array.length;
        int mid = length / 2;
        int j = 0;
        int[] left = new int[mid];
        int[] right = new int[length - mid];
        for (int i = 0; i < length; i++) {
            if (i < mid){
                left[i] = array[i];
            }else{
                right[j] = array[i];
                j++;
            }
        }
        // start counting
        int lp = 0, rp = 0, position = left.length, counter = 0;
        while (lp < left.length && rp < right.length){
            if (left[lp] > right[rp]){
                counter += position;
                rp++;
            }else{
                position--;
                lp++;
            }
        }
        return counter;
    }

    public static long countInversions(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int[] temp = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;
        long inversionCount = 0;

        inversionCount += mergeSort(arr, temp, left, mid);
        inversionCount += mergeSort(arr, temp, mid + 1, right);

        inversionCount += merge(arr, temp, left, mid, right);

        return inversionCount;
    }

    private static long merge(int[] arr, int[] temp, int left, int mid, int right) {
        long inversionCount = 0;

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversionCount += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return inversionCount;
    }


    public static void main(String[] args) {
//        int inversions = countInversion(new int[]{3, 7, 10, 14, 18, 19,
//        2, 11, 16, 17, 23, 25});
//        System.out.println(inversions);

        int[] arr = {1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7};
        long inversions = countInversions(arr);
        System.out.println("Inversion Count: " + inversions);
    }
}
