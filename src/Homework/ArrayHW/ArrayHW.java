package Homework.ArrayHW;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArrayHW<T extends Comparable<T>> {

    private T[] array;
    private int size;

    public ArrayHW(int lenght) {
        this.array = (T[]) new Comparable[lenght];
        this.size = 0;
    }

    public void fillRandom(int min, int max) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof Integer) {
                array[i] = (T) Integer.valueOf(rand.nextInt((max - min + 1)) + min);
            } else if (array[i] instanceof Double) {
                array[i] = (T) Double.valueOf(min + rand.nextDouble() * (max - min));
            } else {
                System.out.println("Генерация случайных значений недоступна для этого типа");
                return;
            }
        }
        this.size = array.length;
    }
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    public T findMax() {
        if (size == 0) return null;
        T max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public T findMin() {
        if (size == 0) return null;
        T min = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    public double average() {
        if (size == 0) return 0;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] instanceof Number) {
                sum += ((Number) array[i]).doubleValue();
            } else {
                System.out.println("Нет поддержки для вычисления среднего для этого типа");
                return 0;
            }
        }
        return sum / size;
    }

    public void sortAsc() {
        Arrays.sort(array, 0, size);
    }


    public void sortDesc() {
        Arrays.sort(array, 0, size, (a, b) -> b.compareTo(a));
    }


    public int binarySearch(T key) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].compareTo(key);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Не найден
    }


    public void replace(int index, T newValue) {
        if (index >= 0 && index < size) {
            array[index] = newValue;
        } else {
            System.out.println("Некорректный индекс");
        }
    }


    public int getSize() {
        return size;
    }


}
