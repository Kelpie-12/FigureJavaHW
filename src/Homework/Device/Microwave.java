package Homework.Device;

public class Microwave extends Device {
    private final int power;
    private final String brand;

    public Microwave(String name, String brand, int power) {
        super(name);
        this.brand = brand;
        this.power = power;
    }

    @Override
    public void Sound() {
        System.out.println("The sound of a microwave oven");
    }

    @Override
    public void Show() {
        System.out.println("Microwave: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", бренд: " + this.brand + ", мощность: " + this.power + " watt");
    }

}
