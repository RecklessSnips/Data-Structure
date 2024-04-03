package Sorting;

public class Insertion_Sort {

    public static void insertionSort(int[] array){
        // 从第二个元素开始
        // 不断将array里的值跟前一个比较，直到直到到第一个比它大的，以及如果
        // 到达了0时都没有符合，则停下
        for (int i = 1; i < array.length; i++) {
            int currValue = array[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && currValue < array[insertIndex]){
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = currValue;
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, -1, 2, -9 , 10, 5, 0};
        insertionSort(test);
        for(int i: test){
            System.out.print(i + " ");
        }
    }
}
