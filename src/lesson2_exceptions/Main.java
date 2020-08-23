package lesson2_exceptions;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] correctArray = {
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}
        };
        String[][] incorrectSizeArray = {
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
        };
        String[][] incorrectDataArray = {
                {"11", "12", "13", "14"},
                {"21", "qqq", "23", "24"},
                {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}
        };

        checkArray(correctArray);
        checkArray(incorrectSizeArray);
        checkArray(incorrectDataArray);
    }

    private static void checkArray(String[][] array) {
        System.out.println();
        try {
            checkMyArraySize(array);
            checkMyArrayData(array);
        } catch (MyArrayDataException dataException) {
            System.out.printf("Исключение MyArrayDataException => Недопустимое значение %s в ячейке [%d][%d]! Ожидалось число.\n", dataException.getValue(), dataException.getRow(), dataException.getColumn());
        } catch (MyArraySizeException sizeException) {
            System.out.println("Исключение MyArraySizeException => Недопустимый размер массива! Ожидался размер 4*4.");
        } finally {
            System.out.println("Проверка массива завершена");
        }
    }

    static void checkMyArrayData(String[][] array) throws MyArrayDataException {
        System.out.println("Проверка данных в массиве: " + toString(array));
        int sum = 0;
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                try {
                    int x = Integer.parseInt(array[row][column]);
                    sum += x;
                } catch (Exception ex) {
                    throw new MyArrayDataException(row, column, array[row][column]);
                }
            }
        }
        System.out.println("Сумма элементов в массиве: " + sum);
    }

    static void checkMyArraySize(String[][] array) throws MyArraySizeException {
        System.out.println("Проверка размера массива: " + toString(array));
        if (array.length != 4) {
            throw new MyArraySizeException();
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException();
            }
        }
    }

    static String toString(String[][] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String[] row : array) {
            stringBuilder.append(Arrays.toString(row)).append(",");
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "]");
        return stringBuilder.toString();
    }
}
