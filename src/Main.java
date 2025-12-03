import Figura.Circle;
import Figura.Figura;
import Homework.Animal.Animal;
import Homework.Animal.Crocodal;
import Homework.Device.Device;
import Homework.Device.Teapot;
import Homework.Human.Human;
import Homework.Human.Pilot;
import Homework.Instrument.MusicalInstrument;
import Homework.Instrument.Trombone;
import Homework.Money.Money;
import Homework.Money.Product;

public class Main {
    public static void main(String[] args) {
        System.out.println("Class Human");
        Human h = new Pilot("Piter", "Parker");
        System.out.println(h.getInfo());


        System.out.println("Class Animal");
        Animal c=new Crocodal();
        System.out.println(c.getInfo());


        System.out.println("Class Money");
        Product item=new Product(1,new Money(100,23),"Test");
        System.out.println(item.getInfo());
        item.setDownPrice(25.4);
        System.out.println(item.getInfo());


        System.out.println("Class Device");
        Device teapot = new Teapot("Test", "Steel", 2);
        teapot.Desc();

        System.out.println("Class Trombone");
        MusicalInstrument m=new Trombone("Copper's Trombone", "Copper", "Big");
        m.Desc();
    }
}
