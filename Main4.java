import java.util.Arrays;

public class Main4 {
    public static void main(String[] args) {
        // Створення та робота з одновимірним масивом
        int[] primitiveArray = new int[]{1, 2, 3, 4, 5};
        printArray(primitiveArray);

        // Зміна розміру масиву
        primitiveArray = resizeArray(primitiveArray, 8);
        printArray(primitiveArray);

        // Створення та робота з матрицею
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printMatrix(matrix);

        // Зміна розміру матриці
        matrix = resizeMatrix(matrix, 4, 3);
        printMatrix(matrix);

        // Перетворення масиву та матриці на рядок
        String arrayString = arrayToString(primitiveArray);
        String matrixString = matrixToString(matrix);

        System.out.println("Масив у вигляді рядка: " + arrayString);
        System.out.println("Матриця у вигляді рядка: " + matrixString);
    }

    // Друк масиву
    private static void printArray(int[] array) {
        System.out.println("Масив: " + Arrays.toString(array));
    }

    // Зміна розміру масиву зі збереженням значень
    private static int[] resizeArray(int[] array, int newSize) {
        int[] newArray = Arrays.copyOf(array, newSize);
        return newArray;
    }

    // Друк матриці
    private static void printMatrix(int[][] matrix) {
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Зміна розміру матриці зі збереженням значень
    private static int[][] resizeMatrix(int[][] matrix, int newRows, int newCols) {
        int[][] newMatrix = new int[newRows][newCols];
        for (int i = 0; i < Math.min(matrix.length, newRows); i++) {
            newMatrix[i] = Arrays.copyOf(matrix[i], newCols);
        }
        return newMatrix;
    }

    // Перетворення масиву на рядок
    private static String arrayToString(int[] array) {
        return Arrays.toString(array);
    }

    // Перетворення матриці на рядок
    private static String matrixToString(int[][] matrix) {
        StringBuilder matrixString = new StringBuilder();
        for (int[] row : matrix) {
            matrixString.append(Arrays.toString(row)).append("\n");
        }
        return matrixString.toString();
    }
}