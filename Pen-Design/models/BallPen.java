package models;
import interfaces.Refillable;

public class BallPen extends Pen implements Refillable {
    private Refill refill;

    public BallPen(String brand, String name, double price, Refill refill) {
        super(brand, name, price);
        this.refill = refill;
    }

    @Override
    public void changeRefill(Refill refill) {
        this.refill = refill;
        System.out.println("BallPen refill changed successfully.");
    }

    @Override
    public void write() {
        if (refill != null && refill.getInk() != null) {
            System.out.println(getName() + " is writing with " + refill.getInk().getColor() + " ball ink.");
        } else {
            System.out.println(getName() + " cannot write. Please insert a refill.");
        }
    }
}
