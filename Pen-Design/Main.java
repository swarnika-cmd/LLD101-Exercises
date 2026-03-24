import models.*;
import interfaces.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Pen Design System Demo ---");

        Ink gelInk = new Ink("Blue", "Gel");
        Nib fineNib = new Nib(0.5);
        Refill reynoldsRefill = new Refill(gelInk, fineNib);

        GelPen trimax = new GelPen("Reynolds", "Trimax", 50.0, reynoldsRefill);
        trimax.write();

        Ink fountainInk = new Ink("Black", "Fountain");
        Nib goldNib = new Nib(0.7);
        FountainPen parker = new FountainPen("Parker", "Vector", 500.0, fountainInk, goldNib);
        parker.write();

        System.out.println("\n--- Changing Ink / Refill ---");
        trimax.changeRefill(new Refill(new Ink("Red", "Gel"), fineNib));
        trimax.write();

        parker.changeInk(new Ink("Blue", "Fountain"));
        parker.write();

        Ink markerInk = new Ink("Green", "Permanent");
        Marker sharpie = new Marker("Sharpie", "Standard", 100.0, markerInk);
        sharpie.write();
    }
}
