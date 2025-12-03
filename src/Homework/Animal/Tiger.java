package Homework.Animal;

public class Tiger extends Animal implements Hunting{
    public Tiger() {
        super();
        setName("Tiger");
        setPower("100");
        setArea("Africa");
        setSpeed("100");
    }


    @Override
    public String hunt() {
        return this.getName()+" is hunting";
    }
}
