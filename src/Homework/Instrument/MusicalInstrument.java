package Homework.Instrument;

public abstract class MusicalInstrument {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected MusicalInstrument(String name) {
        this.name = name;
    }

    public void Sound() {
        System.out.println("The instrument makes a sound");
    }

    public void Show() {
        System.out.println("Instrument: " + name);
    }

    public void Desc() {
        System.out.println("Description: " + name);
    }

    public void History() {
        System.out.println("History of creation: unavailable");
    }
}
