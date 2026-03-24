package models;
import interfaces.Refillable;

public class GelPen extends Pen implements Refillable {
    private Refill refill;

    public GelPen(String brand, String name, double price, Refill refill) {
        super(brand, name, price);
        this.refill = refill;
    }

    @Override
    public void changeRefill(Refill refill) {
        this.refill = refill;
        System.out.println("GelPen refill changed successfully.");
    }

    @Override
    public void write() {
        if (refill != null && refill.getInk() != null) {
            System.out.println(getName() + " is writing smoothly with " + refill.getInk().getColor() + " gel ink.");
        } else {
            System.out.println(getName() + " cannot write. No valid refill found.");
        }
    }
}
