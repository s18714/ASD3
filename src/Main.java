import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static int ROWS, COLS;
    static boolean[][] visited;

    int[][] init(File file) {
        try {
            Scanner scanner = new Scanner(file);
            COLS = scanner.nextInt();
            ROWS = scanner.nextInt();
            int[][] tab = new int[ROWS][COLS];
            visited = new boolean[ROWS][COLS];

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    tab[i][j] = scanner.nextInt();
                }
            }
            return tab;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    int count(int[][] array) {
        int count = 0;
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                if (array[i][j] == 1 && !visited[i][j]) {
                    DFS(array, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void DFS(int[][] array, int row, int col) {
        int[] rowSumator = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colSumator = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        visited[row][col] = true;

        for (int k = 0; k < 8; k++) {
            if (isCorrect(array, row + rowSumator[k], col + colSumator[k]))
                DFS(array, row + rowSumator[k], col + colSumator[k]);
        }
    }
    
    boolean isCorrect(int[][] array, int row, int col) {
        return (row >= 0) && (row < ROWS) && (col >= 0) && (col < COLS) && (array[row][col] == 1 && !visited[row][col]);
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        int[][] tab = main.init(new File(System.getProperty("user.dir") + "/file.txt"));

        System.out.println("Liczba wysp: " + main.count(tab));
    }
}