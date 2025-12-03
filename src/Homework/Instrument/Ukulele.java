package Homework.Instrument;

public class Ukulele extends MusicalInstrument {
    private final String size;
    private final String origin;

    public Ukulele(String name, String size, String origin) {
        super(name);
        this.size = size;
        this.origin = origin;
    }

    @Override
    public void Sound() {
        System.out.println("Ukulele sound: ooh-ooh");
    }

    @Override
    public void Show() {
        System.out.println("Ukulele: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", size: " + this.size + ", origin: " + this.origin);
    }

    @Override
    public void History() {
        System.out.println("History: The ukulele originated in Hawaii in the 19th century. It is a small stringed instrument.");
    }
}
