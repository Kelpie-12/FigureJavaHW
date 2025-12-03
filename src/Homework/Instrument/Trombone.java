package Homework.Instrument;

public class Trombone extends MusicalInstrument {
    private final String material;
    private final String size;

    public Trombone(String name, String material, String size) {
        super(name);
        this.material = material;
        this.size = size;
    }

    @Override
    public void Sound() {
        System.out.println("Trombone sound: brrr");
    }

    @Override
    public void Show() {
        System.out.println("Trombone: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", material: " + this.material + ", size: " + this.size);
    }

    @Override
    public void History() {
        System.out.println("History: The trombone originated in 15th-century Italy. It is a masterful wind instrument.");
    }
}
