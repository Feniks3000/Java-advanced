package lesson5_threads;

import java.util.Arrays;

public class OneThread {
    static final int SIZE = 50000000;
    float[] arr = new float[SIZE];

    public OneThread() {
        Arrays.fill(arr, 1);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finishTime = System.currentTimeMillis();
        System.out.printf("Один поток - Массив обработан за %d мс\n", finishTime - startTime);
        System.out.printf("Один поток - Последний элемент массива равен %f\n", arr[SIZE - 1]);
        System.out.printf("Один поток - Сумма равна %f\n", sum());
    }

    float sum() {
        float result = 0;
        for (float item : arr) {
            result += item;
        }
        return result;
    }
}
