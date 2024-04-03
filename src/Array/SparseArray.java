package Array;

// 见尚硅谷老韩009章

public class SparseArray {
    public static void main(String[] args) {
        int[][] matrix = new int[10][10];
        // 模拟围棋，让1为黑，2为白。当作例子了。
        matrix[1][2] = 1;
        matrix[2][3] = 2;
        matrix[5][5] = 3;
        // Test
        System.out.println("Initialize 2D array: ");
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        System.out.println();

        // 1. traverse 2D array 得到所有不为0的个数sum
        int sum = 0;
        for(int[] arr : matrix){
            for (int ele : arr){
                if (ele != 0)
                    sum++;
            }
        }
        System.out.println("Sum in total : " + sum);
        System.out.println();

        // 2. 创建sparse array （matrix）
        // row col val (always has 3 columns)
        // +1 is for the row 0 to store the matrix info
        int[][] sparseArray = new int[sum + 1][3];

        // 3. 将2D matrix的数据传输到sparse matrix里
        int row = matrix.length;
        int col = matrix[0].length;
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = sum;    // how many value in total in the matrix
        int counter = 0;
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    counter++;
                    sparseArray[counter][0] = i;    // store the row
                    sparseArray[counter][1] = j; // store the col
                    sparseArray[counter][2] = matrix[i][j]; // store the value
                }
            }
        }
        System.out.println("The Sparse array is: ");
        for(int[] r : sparseArray){
            System.out.printf("%d\t%d\t%d\t\n", r[0], r[1], r[2]);
        }

        // 4 Transfer back to the 2D:
        System.out.println();
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println();

        // get the magnitude
        int[][] matrix2 = new int[sparseArray[0][0]][sparseArray[0][1]];

        // read the value index from the sparse array
        for (int i = 1; i < sparseArray.length; i++) {
            int rowNumber = sparseArray[i][0];
            int colNumber = sparseArray[i][1];
            int value = sparseArray[i][2];
            matrix2[rowNumber][colNumber] = value;
            // can write in single step:
            // matrix2[sparseArray[i][0]] [sparseArray[i][1]] = sparseArray[i][2];
        }

        // finally!
        System.out.println("Transfer back to the original matrix:");
        for(int[] s : matrix2){
            for(int e1 : s){
                System.out.printf("%d\t", e1);
            }
            System.out.println();
        }
    }
}
