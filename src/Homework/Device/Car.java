package Homework.Device;

public class Car extends Device {
    private final String model;
    private final int year;

    public Car(String name, String model, int year) {
        super(name);
        this.model = model;
        this.year = year;
    }

    @Override
    public void Sound() {
        System.out.println("The sound of a car engine");
    }

    @Override
    public void Show() {
        System.out.println("Car: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", model: " + this.model + ", year: " + this.year);
    }
}
