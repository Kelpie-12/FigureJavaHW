package Homework.Money;

public class Product {
    private int id;
    private Money price;
    private String name;

    public Product(int id, Money price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
    public String getInfo(){
        return "Product name: "+this.name+" Price: "+this.price.getValue();
    }

    public void setDownPrice(Money money) {
        this.price.setInteger(this.price.getInteger()-money.getInteger());
        this.price.setFraction(this.price.getFraction()-money.getFraction());
    }
    public void setDownPrice(double money) {
        double r=this.price.getValue();
        r-=money;
        price.setValue(r);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
