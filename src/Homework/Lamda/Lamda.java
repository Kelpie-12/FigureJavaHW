package Homework.Lamda;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lamda {

    public void task1() {
        Scanner scanner = new Scanner(System.in);
        Supplier<String> func = () -> {
            String res = new String();
            System.out.println("Введите год");
            int year = scanner.nextInt();
            if (year % 400 == 0 || year % 100 == 0 || year % 4 == 0) {
                res = "Ваш год высокосный";

            } else res = "Ваш год не высокосный";
            return res;
        };
        System.out.println(func.get());

        func = () -> {
            String res = new String();
            System.out.println("Введите первую дату ");
            Date date1 = new Date();
            System.out.println("Год ");
            date1.setYear(scanner.nextInt());
            System.out.println("Месяц ");
            date1.setMonth(scanner.nextInt());
            System.out.println("День ");
            date1.setDate(scanner.nextInt());


            System.out.println("Введите вторую дату ");
            Date date2 = new Date();
            System.out.println("Год ");
            date2.setYear(scanner.nextInt());
            System.out.println("Месяц ");
            date2.setMonth(scanner.nextInt());
            System.out.println("День ");
            date2.setDate(scanner.nextInt());


            if (date1.getTime() > date2.getTime()) {
                long r = date1.getTime() - date2.getTime();
                r = r / 60000;
                r = r / 1440;
                res = "Разница в днях " + r;
            } else {
                long r = date2.getTime() - date1.getTime();
                r = r / 60000;
                r = r / 1440;
                res = "Разница в днях " + r;
            }
            return res;
        };
        System.out.println(func.get());

        func = () -> {
            String res = new String();
            System.out.println("Введите первую дату ");
            Date date1 = new Date();
            System.out.println("Год ");
            date1.setYear(scanner.nextInt());
            System.out.println("Месяц ");
            date1.setMonth(scanner.nextInt());
            System.out.println("День ");
            date1.setDate(scanner.nextInt());


            System.out.println("Введите вторую дату ");
            Date date2 = new Date();
            System.out.println("Год ");
            date2.setYear(scanner.nextInt());
            System.out.println("Месяц ");
            date2.setMonth(scanner.nextInt());
            System.out.println("День ");
            date2.setDate(scanner.nextInt());


            if (date1.getTime() > date2.getTime()) {
                long r = date1.getTime() - date2.getTime();
                r = r / 60000;
                r = r / 1440;
                res = "Разница в неделях " + r / 7;
            } else {
                long r = date2.getTime() - date1.getTime();
                r = r / 60000;
                r = r / 1440;
                res = "Разница в неделях " + r / 7;
            }
            return res;
        };
        System.out.println(func.get());


        func = () -> {
            String res = new String();
            System.out.println("Введите дату ");

            System.out.println("Год ");
            int y = scanner.nextInt();
            System.out.println("Месяц ");
            int m = scanner.nextInt();
            System.out.println("День ");
            int d = scanner.nextInt();

            LocalDate date1 = LocalDate.of(y, m, d);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d M yyyy");
            LocalDate date = LocalDate.parse(date1.getDayOfWeek().getValue() + " " + date1.getMonthValue() + " " + date1.getYear(), formatter);


            DayOfWeek da = date.getDayOfWeek();
            int dayOfWeek = da.getValue();
            if (dayOfWeek == 1) {
                res = "Понедельник";
            } else if (dayOfWeek == 2) {
                res = "Вторник";
            } else if (dayOfWeek == 3) {
                res = "Вторник";
            } else if (dayOfWeek == 4) {
                res = "Среда";
            } else if (dayOfWeek == 5) {
                res = "Пятница";
            } else if (dayOfWeek == 6) {
                res = "Суббота";
            } else res = "Воскресенье";

            return res;
        };
        System.out.println(func.get());

        scanner.close();

    }

    public void task2() {

        BiFunction<Double, Double, Double> sum = (a, b) -> a + b;


        BiFunction<Double, Double, Double> difference = (a, b) -> a - b;


        BiFunction<Double, Double, Double> product = (a, b) -> a * b;


        BiFunction<Double, Double, Double> division = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Деление на ноль");
            }
            return a / b;
        };


        double num1 = 1.5;
        double num2 = 0.5;


        System.out.println("Сумма: " + sum.apply(num1, num2));
        System.out.println("Разница: " + difference.apply(num1, num2));
        System.out.println("Произведение: " + product.apply(num1, num2));
        System.out.println("Деление: " + division.apply(num1, num2));
    }

    public void task3() {
        Function<int[], Integer> maxOfFour = nums -> {
            int max = nums[0];
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }
            }
            return max;
        };


        Function<int[], Integer> minOfFour = nums -> {
            int min = nums[0];
            for (int num : nums) {
                if (num < min) {
                    min = num;
                }
            }
            return min;
        };


        int[] numbers = {23, 7, 45, 12};


        System.out.println("Максимум из четырёх: " + maxOfFour.apply(numbers));
        System.out.println("Минимум из четырёх: " + minOfFour.apply(numbers));
    }

    public void task4() {
        int[] array = {1, 5, -3, 8, 4, -2, 7, 10};


        Predicate<Integer> isEqualToFive = num -> num == 5;
        System.out.println("Сумма элементов равных 5: " + sumByCondition(array, isEqualToFive));


        int A = 2;
        int B = 8;
        Predicate<Integer> notInRange = num -> num < A || num > B;
        System.out.println("Сумма чисел не в диапазоне 2-8: " + sumByCondition(array, notInRange));


        Predicate<Integer> isPositive = num -> num > 0;
        System.out.println("Сумма положительных чисел: " + sumByCondition(array, isPositive));


        Predicate<Integer> isNegative = num -> num < 0;
        System.out.println("Сумма отрицательных чисел: " + sumByCondition(array, isNegative));
    }


    public static int sumByCondition(int[] array, Predicate<Integer> condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }
}

