package Homework.Device;

public class Steamboat extends Device {
    private final String route;
    private final int capacity; // пассажиров

    public Steamboat(String name, String route, int capacity) {
        super(name);
        this.route = route;
        this.capacity = capacity;
    }

    @Override
    public void Sound() {
        System.out.println("The sound of a steamship");
    }

    @Override
    public void Show() {
        System.out.println("Steamship: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", route: " + this.route + ", capacity: " + this.capacity);
    }
}
