package Homework.Human;

public class Builder extends Human{
    public Builder(String firstName, String lastName) {
        super(firstName, lastName);
        setProfession("Builder");
    }

    public void Building(){
        System.out.println("Builder is build");
    }
}
