package lesson5_threads;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class TwoThreads {
    static final int SIZE = 50000000;
    static final int HALF = SIZE / 2;
    float[] arr = new float[SIZE];

    public TwoThreads() {
        Arrays.fill(arr, 1);
        long startTime = System.currentTimeMillis();
        float[] firstArr = Arrays.copyOfRange(arr, 0, HALF);
        float[] secondArr = Arrays.copyOfRange(arr, HALF, SIZE);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                firstArr[i] = (float) (firstArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Первый поток завершился");
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                secondArr[i] = (float) (secondArr[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
            System.out.println("Второй поток завершился");
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arr = ArrayUtils.addAll(firstArr, secondArr);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Два потока - Массив обработан за %d мс\n", finishTime - startTime);
        System.out.printf("Два потока - Последний элемент массива равен %f\n", arr[SIZE - 1]);
        System.out.printf("Два потока - Сумма равна %f\n", sum());
    }

    float sum() {
        float result = 0;
        for (float item : arr) {
            result += item;
        }
        return result;
    }
}
