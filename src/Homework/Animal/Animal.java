package Homework.Animal;

public abstract class Animal {
    private String name;
    private String power;
    private String speed;
    private String area;

    public String getInfo() {
        return "Name: " + this.name + " Area: " + this.area + " Speed: " + this.speed + " Power: " + this.power;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }


    protected Animal() {

    }
}
