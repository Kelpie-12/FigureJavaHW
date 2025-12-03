package Homework.Device;

public class Teapot extends Device {

    private final String material;
    private final int volume; // в литрах

    public Teapot(String name, String material, int volume) {
        super(name);
        this.material = material;
        this.volume = volume;
    }

    @Override
    public void Sound() {
        System.out.println("The sound of a boiling kettle");
    }

    @Override
    public void Show() {
        System.out.println("Teapot: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", material: " + this.material + ", volume: " +this.volume + " l.");
    }
}
