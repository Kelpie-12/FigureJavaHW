package Homework.Threads;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsHW {

    private static final int ARRAY_SIZE = 10; // Размер массива
    private static int[] array = new int[ARRAY_SIZE];

    private static double sum = 0;
    private static double average = 0;

    private static final Object lock = new Object();
    private static boolean isFilled = false;

    public void task1(){
        Thread fillerThread = new Thread(() -> {
            Random random = new Random();
            synchronized (lock) {
                for (int i = 0; i < ARRAY_SIZE; i++) {
                    array[i] = random.nextInt(100);
                }
                isFilled = true;
                lock.notifyAll();
            }
        });

        Thread sumThread = new Thread(() -> {
            waitForFill();
            double localSum = 0;
            for (int num : array) {
                localSum += num;
            }
            synchronized (ThreadsHW.class) {
                sum = localSum;
            }
        });

        Thread avgThread = new Thread(() -> {
            waitForFill();
            double localSum = 0;
            for (int num : array) {
                localSum += num;
            }
            synchronized (ThreadsHW.class) {
                average = localSum / ARRAY_SIZE;
            }
        });


        fillerThread.start();


        sumThread.start();
        avgThread.start();

       try {
           fillerThread.join();
           sumThread.join();
           avgThread.join();
       }catch (Exception e){
           System.out.println("Ошибка при присоединение потока: " + e.getMessage());
       }

        System.out.println("Массив: " + java.util.Arrays.toString(array));
        System.out.println("Сумма элементов: " + sum);
        System.out.println("Среднее значение: " + average);
    }


    private static int totalNumbers = 0;
    private static int primeCount = 0;
    private static AtomicInteger factorialProcessedCount = new AtomicInteger(0);

    public void task2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        String filledFilePath = filePath + "_filled.txt";
        String primesFilePath = filePath + "_primes.txt";
        String factorialsFilePath = filePath + "_factorials.txt";

        Thread fillerThread = new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filledFilePath))) {
                Random rand = new Random();
                int count = 20;
                for (int i = 0; i < count; i++) {
                    int num = rand.nextInt(1000);
                    writer.write(num + " ");
                }
                synchronized (lock) {
                    totalNumbers = count;
                    isFilled = true;
                    lock.notifyAll();
                }
                System.out.println("Массив заполнен");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread primeThread = new Thread(() -> {
            waitForFill();
            List<Integer> primes = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filledFilePath));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(primesFilePath))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] nums = line.split("\\s+");
                    for (String s : nums) {
                        int num = Integer.parseInt(s);
                        if (isPrime(num)) {
                            primes.add(num);
                        }
                    }
                }
                // Запись простых чисел
                for (int prime : primes) {
                    writer.write(prime + " ");
                }
                synchronized (ThreadsHW.class) {
                    primeCount = primes.size();
                }
                System.out.println("Найдено простых чисел: " + primeCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread factorialThread = new Thread(() -> {
            waitForFill();
            try (BufferedReader reader = new BufferedReader(new FileReader(filledFilePath));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(factorialsFilePath))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] nums = line.split("\\s+");
                    for (String s : nums) {
                        int num = Integer.parseInt(s);
                        long fact = factorial(num);
                        writer.write("Число: " + num + ", факториал: " + fact + "\n");
                        factorialProcessedCount.incrementAndGet();
                    }
                }
                System.out.println("Факториалы вычислены для " + factorialProcessedCount.get() + " чисел");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        fillerThread.start();
        primeThread.start();
        factorialThread.start();


        try {
            fillerThread.join();
            primeThread.join();
            factorialThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Статистика:");
        System.out.println("Общее количество чисел: " + totalNumbers);
        System.out.println("Количество простых чисел: " + primeCount);
        System.out.println("Количество обработанных факториалов: " + factorialProcessedCount.get());
        System.out.println("Программа завершена");
    }


    private static void waitForFill() {
        synchronized (lock) {
            while (!isFilled) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static long factorial(int num) {
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;

        }
        return result;
    }

}
