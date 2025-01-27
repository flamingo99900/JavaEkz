import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int row = scn.nextInt();

        System.out.print("Enter number of columns: ");
        int col = scn.nextInt();

        int[][] arr= new int[row][col];

        Random rand = new Random();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j]=  rand.nextInt(1000);
            }
        }

        // Printing Elements of Arrays
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }
        scn.close();
        int max_index_row = 0;
        int max_index_column = 0;

        int min_index_row = 0;
        int min_index_column = 0;

        int max = arr[0][0];
        int min = arr[0][0];

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if (max < arr[i][j])
                {
                    max = arr[i][j];
                    max_index_row = i;
                    max_index_column = j;

                }

                if (min > arr[i][j])
                {
                    min = arr[i][j];
                    min_index_row = i;
                    min_index_column = j;
                }
            }
        }
        System.out.println("Min: " + min + " Индексы row и column: "+ min_index_row + " " + min_index_column +
                "\n" + "Max: " + max + " Индексы row и column: "+ max_index_row + " " + max_index_column);

    }
}
