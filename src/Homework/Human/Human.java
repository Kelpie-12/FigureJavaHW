package Homework.Human;

public abstract class Human {
    private String firstName;
    private String lastName;
    private int age;
    private String profession;

    protected Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getInfo() {
        return "First name: " + this.firstName + " Last name: " + this.lastName + " Profession: " + this.profession;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
