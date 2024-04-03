package Sorting;

public class Selection_Sort {

    public static void selectionSort(int[] array){
        // find the smallest term accordingly, from a[0] to a[n-1]
        for (int i = 0; i < array.length; i++) {
            int smallest = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < smallest){
                    int tmp = array[j];
                    array[j] = smallest;
                    smallest = tmp;
                }
            }
            array[i] = smallest;
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, -1, 2, -9 , 10, 5, 0};
        selectionSort(test);
        for(int i: test){
            System.out.print(i + " ");
        }
    }
}
