package Homework.Device;

public abstract class Device {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Device(String name) {
        this.name = name;
    }

    public void Sound() {
        System.out.println("The device makes a sound");
    }

    public void Show() {
        System.out.println("Device: " + name);
    }

    public void Desc() {
        System.out.println("Description device: " + name);
    }
}