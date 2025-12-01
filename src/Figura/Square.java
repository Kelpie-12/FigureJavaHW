package Figura;

public class Square extends Figura{
    private double aSide;
    public Square(double a){
        setSideA(a);
    }
    public void setSideA(double a) {
        this.aSide = a;
    }

    @Override
    public double getSqure() {
        return 0;
    }
}
