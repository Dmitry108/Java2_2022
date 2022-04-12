package exceptions;

// Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
// при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
// Далее метод должен пройтись по всем элементам массива, преобразовать в int,
// и просуммировать. Если в каком-то элементе массива преобразование не удалось (например,
// в ячейке лежит символ или текст вместо числа), должно быть брошено исключение
// MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
// В методе main() вызвать полученный метод, обработать возможные исключения
// MySizeArrayException и MyArrayDataException, и вывести результат расчета.

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[][] arrayCorrect = {
                {"0", "1", "2", "3"},
                {"4", "5", "6", "7"},
                {"8", "9", "10", "11"},
                {"12", "13", "14", "15"}};
        String[][] arrayNotSquare = {
                {"0", "1", "2", "3"},
                {"4", "5", "6", "7", "7.5"},
                {"8", "9", "10", "11"},
                {"12", "13", "14", "15"}};
        String[][] arrayNotOnlyInteger = {
                {"0", "1", "2", "3"},
                {"4", "5", "6", "7"},
                {"8", "9", "10", "11?"},
                {"12", "13", "14", "15"}};

        trySumStringArray(arrayNotSquare);
        trySumStringArray(arrayNotOnlyInteger);
        trySumStringArray(arrayCorrect);
    }

    public static void trySumStringArray(String[][] array) {
        boolean isCorrect = true;
        do {
            try {
                System.out.printf("Сумма чисел массива %d%n", sumArray(array));
                isCorrect = true;
            } catch (MyArraySizeException e) {
                e.printStackTrace();
            } catch (MyArrayDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите корректное значение: ");
                array[e.getRow()][e.getCol()] = scanner.next();
                isCorrect = false;
            }
        } while (!isCorrect);
    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        checkArraySize(array, 4);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }
        return sum;
    }

    private static void checkArraySize(String[][] array, int size) throws MyArraySizeException {
        if (array.length != size) throw new MyArraySizeException("размерность массива нарушена");
        for (String[] row : array) {
            if (row.length != size) throw new MyArraySizeException("размерность массива нарушена");
        }
    }
}