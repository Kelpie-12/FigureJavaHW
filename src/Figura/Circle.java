package Figura;

public class Circle extends Figura {
    private double radius;
    public Circle(double radius){
        setRadius(radius);
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getSqure() {
        double sq=Math.pow(Math.PI, 2)*radius;
        return sq;
    }
}
