package Classwork;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streems {
    public void task1() {
        int lenght = 30;
        int[] arr = new int[lenght];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        long chetCount = Arrays.stream(arr).filter(i -> i % 2 == 0).count();
        long neChetCount = Arrays.stream(arr).filter(i -> i % 2 == 1).count();
        long zeroCount = Arrays.stream(arr).filter(i -> i == 0).count();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Сгенерированные числа: " + printArray(arr));
        System.out.println("Количество четных чисел: " + chetCount);
        System.out.println("Количество нечетных чисел: " + neChetCount);
        System.out.println("Количество равных нулю: " + zeroCount);

        System.out.println("Введите свое число: ");
        int userNumber = scanner.nextInt();
        long userCount = Arrays.stream(arr).filter(i -> i == userNumber).count();

        System.out.println("Количество равных вашему числу: " + userCount);
        scanner.close();
    }

    public void task2() {
        Random random = new Random();
        List<Cars> cars = IntStream.range(0, 10).mapToObj(i -> {
            return new Cars("abc", random.nextInt(1900, 2025), new Color(random.nextInt(1900, 2025)), random.nextDouble(100.0));
        }).toList();


        Scanner scanner = new Scanner(System.in);

        System.out.println("Все автомобили:");
        cars.forEach(System.out::println);


       // System.out.println("\nВведите цвет для поиска:");
      //  String userColor = scanner.nextLine();
        Color userColor=new Color(1321);
        List<Cars> carsByColor = cars.stream()
                .filter(car -> car.getColor()==(userColor))
                .collect(Collectors.toList());
        System.out.println("Автомобили цвета '" + userColor + "':");
        carsByColor.forEach(System.out::println);


        System.out.println("\nВведите минимальную цену:");
        double minPrice = scanner.nextDouble();
        List<Cars> expensiveCars = cars.stream()
                .filter(car -> car.getPrice() > minPrice)
                .collect(Collectors.toList());
        System.out.println("Автомобили дороже " + minPrice + ":");
        expensiveCars.forEach(System.out::println);


        System.out.println("\nВведите начальный год диапазона:");
        int startYear = scanner.nextInt();
        System.out.println("Введите конечный год диапазона:");
        int endYear = scanner.nextInt();

        List<Cars> carsInRange = cars.stream()
                .filter(car -> car.getYear() >= startYear && car.getYear() <= endYear)
                .collect(Collectors.toList());
        System.out.println("Автомобили в диапазоне годов " + startYear + "-" + endYear + ":");
        carsInRange.forEach(System.out::println);

        scanner.close();

    }

    private String printArray(int[] ar) {
        String res = new String();
        for (int i = 0; i < ar.length; i++) {
            res += " " + ar[i];
        }
        return res;
    }


    class Cars {

        public Cars(String name, int year, Color colour, double price) {
            setColour(colour);
            setName(name);
            setPrice(price);
            setYear(year);
        }

        String name;
        int year;

        public Color getColor() {
            return colour;
        }

        public void setColour(Color colour) {
            this.colour = colour;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        double price;
        Color colour;
    }
}
