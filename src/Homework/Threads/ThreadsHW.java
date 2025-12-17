package Homework.Threads;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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


    static AtomicInteger filesCopied = new AtomicInteger(0);
    static AtomicInteger dirsCopied = new AtomicInteger(0);
    static AtomicInteger errors = new AtomicInteger(0);

    public void task3(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к исходной директории:");
        String sourcePathStr = scanner.nextLine();
        System.out.println("Введите путь к новой директории:");
        String targetPathStr = scanner.nextLine();

        Path sourcePath = Paths.get(sourcePathStr);
        Path targetPath = Paths.get(targetPathStr);

        if (!Files.exists(sourcePath) || !Files.isDirectory(sourcePath)) {
            System.out.println("Исходная директория не существует или не является директорией.");
            return;
        }


        try {
            Files.createDirectories(targetPath);
        } catch (IOException e) {
            System.out.println("Не удалось создать целевую директорию: " + e.getMessage());
            return;
        }


        Thread copyThread = new Thread(() -> {
            try {
                Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        Path relativePath = sourcePath.relativize(dir);
                        Path targetDir = targetPath.resolve(relativePath);
                        try {
                            Files.createDirectories(targetDir);
                            dirsCopied.incrementAndGet();
                        } catch (IOException e) {
                            System.out.println("Ошибка при создании папки: " + targetDir + " - " + e.getMessage());
                            errors.incrementAndGet();
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        Path relativePath = sourcePath.relativize(file);
                        Path targetFile = targetPath.resolve(relativePath);
                        try {
                            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                            filesCopied.incrementAndGet();
                        } catch (IOException e) {
                            System.out.println("Ошибка при копировании файла: " + file + " - " + e.getMessage());
                            errors.incrementAndGet();
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                System.out.println("Ошибка обхода директории: " + e.getMessage());
            }
        });

        copyThread.start();

        try {
            copyThread.join();
        } catch (InterruptedException e) {
            System.out.println("Копирование было прервано");
        }


        System.out.println("Копирование завершено:");
        System.out.println("Файлов скопировано: " + filesCopied.get());
        System.out.println("Папок скопировано: " + dirsCopied.get());
        System.out.println("Ошибок: " + errors.get());

        scanner.close();
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
