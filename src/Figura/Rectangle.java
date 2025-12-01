package Figura;

public class Rectangle extends Figura {
    private double aSide;
    private double bSide;

    public Rectangle(double a, double b) {
        setSideA(a);
        setSideB(b);
    }

    public void setSideA(double a) {
        this.aSide = a;
    }

    public void setSideB(double b) {
        this.bSide = b;
    }

    @Override
    public double getSqure() {
        return this.aSide*this.bSide;
    }
}
