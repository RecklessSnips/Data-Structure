package Sorting;

public class Bubble_Sort {

    public static void bubbleSort(int[] array){
        // keep swapping until not swap occurs, then stop
        boolean ifSwapped = true;
        while(ifSwapped){
            ifSwapped = false;
            for(int i = 0; i < array.length - 1; i++){
                if (array[i + 1] < array[i]){
                    ifSwapped = true;
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i+1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, -1, 2, -9 , 10, 5};
        bubbleSort(test);
        for(int i: test){
            System.out.print(i + " ");
        }
    }
}
