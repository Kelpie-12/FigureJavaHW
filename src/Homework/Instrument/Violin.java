package Homework.Instrument;

public class Violin extends MusicalInstrument{
    private final String material;
    private final String type;

    public Violin(String name, String material, String type) {
        super(name);
        this.material = material;
        this.type = type;
    }

    @Override
    public void Sound() {
        System.out.println("Violin sound: tiu-tuu");
    }

    @Override
    public void Show() {
        System.out.println("Violin: " + this.getName());
    }

    @Override
    public void Desc() {
        System.out.println("Description: " + this.getName() + ", material: " + material + ", type: " + type);
    }

    @Override
    public void History() {
        System.out.println("History: The violin originated in the 16th century in Italy. It is a string instrument.");
    }
}
