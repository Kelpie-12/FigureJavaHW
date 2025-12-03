package Homework.Money;

import java.text.DecimalFormat;

public class Money {
    private int integer;
    private int fraction;

    public Money(int integer, int fraction) {
        this.fraction = fraction;
        this.integer = integer;
    }

    public void setValue(double value) {
        String strNumber = new DecimalFormat("#0.00").format(value);
        String[] parts = strNumber.split(",");
        this.integer = Integer.parseInt(parts[0]);
        this.fraction = Integer.parseInt(parts[1]);
    }


    public double getValue() {
        String res = this.integer + "." + this.fraction;
        return Double.parseDouble(res);
    }

    public void printValue() {
        System.out.println(getValue());
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }
}
