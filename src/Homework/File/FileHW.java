package Homework.File;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHW {

    public void task1() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите путь к первым файлам: ");
        String path1 = scanner.nextLine();

        System.out.print("Введите путь ко вторым файлам: ");
        String path2 = scanner.nextLine();

        try (
                BufferedReader reader1 = new BufferedReader(new FileReader(path1));
                BufferedReader reader2 = new BufferedReader(new FileReader(path2))
        ) {
            String line1, line2;
            int lineNumber = 1;
            boolean filesAreEqual = true;


            while (true) {
                line1 = reader1.readLine();
                line2 = reader2.readLine();


                if (line1 == null && line2 == null) {
                    break;
                }

                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    System.out.println("Несовпадающая строка на линии " + lineNumber + ":");
                    if (line1 != null) {
                        System.out.println("Из файла 1: " + line1);
                    } else {
                        System.out.println("Из файла 1: [отсутствует]");
                    }
                    if (line2 != null) {
                        System.out.println("Из файла 2: " + line2);
                    } else {
                        System.out.println("Из файла 2: [отсутствует]");
                    }
                    filesAreEqual = false;
                }

                lineNumber++;
            }

            if (filesAreEqual) {
                System.out.println("Все строки совпадают.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Один из указанных файлов не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        scanner.close();
    }

    public void task2() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        String longestLine = null;
        int maxLength = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.length() > maxLength) {
                    maxLength = line.length();
                    longestLine = line;
                }
            }

            if (longestLine != null) {
                System.out.println("Самая длинная строка: " + longestLine);
                System.out.println("Длина строки: " + maxLength);
            } else {
                System.out.println("Файл пустой или не содержит строк.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        scanner.close();
    }

    public void task3() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        List<int[]> arrays = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] arr = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    arr[i] = Integer.parseInt(parts[i]);
                }
                arrays.add(arr);
            }

            int totalSumAllArrays = 0;
            int globalMax = Integer.MIN_VALUE;
            int globalMin = Integer.MAX_VALUE;


            for (int i = 0; i < arrays.size(); i++) {
                int[] arr = arrays.get(i);
                int sum = 0;
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                System.out.println("Массив " + (i + 1) + ": " + Arrays.toString(arr));

                for (int num : arr) {
                    sum += num;
                    if (num > max) max = num;
                    if (num < min) min = num;
                }

                System.out.println("Максимум массива " + (i + 1) + ": " + max);
                System.out.println("Минимум массива " + (i + 1) + ": " + min);
                System.out.println("Сумма элементов массива " + (i + 1) + ": " + sum);
                System.out.println();

                totalSumAllArrays += sum;
                if (max > globalMax) globalMax = max;
                if (min < globalMin) globalMin = min;
            }

            if (!arrays.isEmpty()) {
                System.out.println("Максимальный элемент среди всех массивов: " + globalMax);
                System.out.println("Минимальный элемент среди всех массивов: " + globalMin);
                System.out.println("Общая сумма всех элементов: " + totalSumAllArrays);
            } else {
                System.out.println("Файл пустой или не содержит данных.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования числа: " + e.getMessage());
        }
        scanner.close();
    }

    public void task4() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();


        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        int[] array = new int[size];


        System.out.println("Введите элементы массива через пробел:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }


        int[] reversedArray = new int[size];
        for (int i = 0; i < size; i++) {
            reversedArray[i] = array[size - 1 - i];
        }


        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        for (int num : array) {
            if (num % 2 == 0) {
                evens.add(num);
            } else {
                odds.add(num);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Первая строка - исходный массив
            writer.write(arrayToString(array));
            writer.newLine();

            // Вторая строка - четные
            writer.write(arrayToString(evens));
            writer.newLine();

            // Третья строка - нечетные
            writer.write(arrayToString(odds));
            writer.newLine();

            // Четвертая строка - перевернутый массив
            writer.write(arrayToString(reversedArray));
            writer.newLine();

            System.out.println("Данные успешно записаны в файл.");

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
        scanner.close();
    }

    public void task5(){

    }


    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

    private static String arrayToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

}
