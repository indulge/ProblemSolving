package sg.iv.amazon;

public class SpiralArrayPrint {
    public static void printSpiralMatrix(int[][] matrix) {
        //int circleNum = 0;
        int minRow = 0;
        int maxRow = matrix.length;
        int leftCol = 0;
        int rightCol = matrix[0].length;
        
        int i = 0;
        
        int numElem = maxRow * rightCol;

        System.out.println("");
        
        while (i < numElem) {
            // print first row
//          System.out.println("\nfirst row");
            for (int k = leftCol; k < rightCol - 1; k++, i++) {
                System.out.print(matrix[minRow][k] + " ");
            }
             
            
            // print right col
//          System.out.println("\nright col");
            for (int k = minRow; k < maxRow - 1; k++, i++) {
                System.out.print(matrix[k][rightCol - 1] + " ");
            }
            
//          System.out.println("\nlast row");
            //print last row
            for (int k = rightCol - 1; k >= (leftCol + 1); k--, i++) {
                System.out.print(matrix[maxRow - 1][k] + " ");
            }
            
//          System.out.println("\nleft col");
            // print left col
            for (int k =  maxRow - 1; k >= minRow + 1; k--, i++) {
                System.out.print(matrix[k][leftCol] + " ");
            }
            
            minRow++;
            leftCol++;
            maxRow--;
            rightCol--;
//          System.out.println("");
//          System.out.println("minRow: "+minRow);
//          System.out.println("leftCol: "+leftCol);
//          System.out.println("maxRow: "+maxRow);
//          System.out.println("rightCol: "+rightCol);
            /*
            if (i + 1 == numElem) {
                System.out.print(matrix[minRow][leftCol] + " ");
                i++;
            }
            */
            //if only one row or col is left print it out
            if (maxRow - minRow == 1) {
                for (int k =  leftCol; k < rightCol; k++, i++) {
                    System.out.print(matrix[minRow][k] + " ");
                }
            } else if (rightCol - leftCol == 1) {
                for (int k =  minRow; k < maxRow; k--, i++) {
                    System.out.print(matrix[k][leftCol] + " ");
                }
            }
        }
        
        System.out.println("\n\n");
        
        
        
        // print left col

    }

    public static void printArr(int[][] arr) {
        int ROW = arr.length;
        int COL = arr[0].length;
        System.out.println("\n\n---start---");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL ; j++) {
            //arr[i][j] = i * COL + j;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("---end---\n\n");
    }
    public static void main(String[] args) {
      int arr[][] = { { 3, 4, 5, 12, 2 }, { 33, 1, -9, 28, 55 },
              { 7, -5, 8, 66, 21 } };
//        int ROW = 3;
//        int COL = 3;
//        int arr[][] = new int[ROW][COL];
//        for (int i = 0; i < ROW; i++) {
//            for (int j = 0; j < COL ; j++) {
//                arr[i][j] = i * COL + j;
//            }
//        }

        printArr(arr);
        SpiralArrayPrint.printSpiralMatrix(arr);
    }
}