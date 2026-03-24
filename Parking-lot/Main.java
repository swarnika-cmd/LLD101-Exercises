import models.*;
import gates.*;
import strategy.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // set up slots for floor 1
        List<Slot> floor1Slots = new ArrayList<>();
        floor1Slots.add(new Slot(1, SlotType.TWO_WHEELER));
        floor1Slots.add(new Slot(2, SlotType.TWO_WHEELER));
        floor1Slots.add(new Slot(3, SlotType.FOUR_WHEELER));
        floor1Slots.add(new Slot(4, SlotType.FOUR_WHEELER));

        // set up slots for floor 2
        List<Slot> floor2Slots = new ArrayList<>();
        floor2Slots.add(new Slot(5, SlotType.TWO_WHEELER));
        floor2Slots.add(new Slot(6, SlotType.FOUR_WHEELER));
        floor2Slots.add(new Slot(7, SlotType.FOUR_WHEELER));

        List<Floor> floors = new ArrayList<>();
        floors.add(new Floor(1, floor1Slots));
        floors.add(new Floor(2, floor2Slots));

        // gates — both entry gates use the same strategy for now
        SlotAllocationStrategy strategy = new NearestFirstStrategy();
        List<EntryGate> entryGates = new ArrayList<>();
        entryGates.add(new EntryGate(1, strategy));
        entryGates.add(new EntryGate(2, strategy));

        List<ExitGate> exitGates = new ArrayList<>();
        exitGates.add(new ExitGate(1));

        ParkingLot lot = new ParkingLot("City Center Parking", floors, entryGates, exitGates);
        System.out.println("=== " + lot.getName() + " ===");
        System.out.println(lot);
        System.out.println();

        // park some vehicles
        Vehicle bike1 = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        Vehicle car1  = new Vehicle("MH-12-AB-5678", VehicleType.FOUR_WHEELER);
        Vehicle car2  = new Vehicle("DL-03-CD-9012", VehicleType.FOUR_WHEELER);
        Vehicle bike2 = new Vehicle("TN-07-XY-3456", VehicleType.TWO_WHEELER);

        Ticket t1 = lot.park(bike1, 1);
        Ticket t2 = lot.park(car1, 1);
        Ticket t3 = lot.park(car2, 2);
        Ticket t4 = lot.park(bike2, 1);

        System.out.println("--- Tickets Issued ---");
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println();

        System.out.println("Active vehicles: " + lot.getActiveTicketCount());
        System.out.println();

        // unpark car1
        Bill bill = lot.unpark(t2.getTicketId(), 1);
        System.out.println(bill.generateReceipt());
        System.out.println();

        System.out.println("Active vehicles after exit: " + lot.getActiveTicketCount());

        // park another car — should go to the slot that just freed up
        Vehicle car3 = new Vehicle("GJ-05-EF-7890", VehicleType.FOUR_WHEELER);
        Ticket t5 = lot.park(car3, 2);
        System.out.println();
        System.out.println("--- New Ticket ---");
        System.out.println(t5);
        System.out.println("Active vehicles: " + lot.getActiveTicketCount());
    }
}
