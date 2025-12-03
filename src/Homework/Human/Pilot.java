package Homework.Human;

public class Pilot extends Human{
    private String license;

    public Pilot(String firstName, String lastName) {
        super(firstName, lastName);
        setProfession("Pilot");
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
    public void Flying(){
        System.out.println("Pilot is fly");
    }
}
