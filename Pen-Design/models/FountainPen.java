package models;
import interfaces.InkChangeable;

public class FountainPen extends Pen implements InkChangeable {
    private Ink ink;
    private Nib nib;

    public FountainPen(String brand, String name, double price, Ink ink, Nib nib) {
        super(brand, name, price);
        this.ink = ink;
        this.nib = nib;
    }

    public void changeNib(Nib nib) {
        this.nib = nib;
        System.out.println("FountainPen nib changed.");
    }

    @Override
    public void changeInk(Ink ink) {
        this.ink = ink;
        System.out.println("FountainPen ink replaced.");
    }

    @Override
    public void write() {
        if (ink != null && nib != null) {
            System.out.println(getName() + " is writing elegantly with " + ink.getColor() + " ink using a " + nib.getRadius() + "mm nib.");
        } else {
            System.out.println(getName() + " cannot write. Check ink and nib.");
        }
    }
}
