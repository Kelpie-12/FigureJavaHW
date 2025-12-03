package Homework.Animal;

public class Crocodal extends Animal implements Swimming{
    public Crocodal() {
        super();
        setName("Crocodal");
        setPower("85");
        setArea("Africa");
        setSpeed("85");
    }

    @Override
    public String swim() {
        return this.getName()+" is swim";
    }
}
