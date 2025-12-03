package Homework.Animal;

public class Kangaroo extends Animal implements Jamping{
    public Kangaroo() {
        super();
        setName("Kangaroo");
        setPower("70");
        setSpeed("150");
        setArea("Africa");
    }

    @Override
    public String jump() {
        return this.getName()+ " is jump";
    }
}
