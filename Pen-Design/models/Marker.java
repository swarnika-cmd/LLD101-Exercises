package models;

public class Marker extends Pen {
    private Ink ink;

    public Marker(String brand, String name, double price, Ink ink) {
        super(brand, name, price);
        this.ink = ink;
    }

    @Override
    public void write() {
        if (ink != null) {
            System.out.println(getName() + " is making bold marks with " + ink.getColor() + " ink.");
        } else {
            System.out.println(getName() + " is dry.");
        }
    }
}
