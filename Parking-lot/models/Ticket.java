package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private static int counter = 0;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final int ticketId;
    private final int entryGateId;
    private final Vehicle vehicle;
    private final Slot slot;
    private final int floorNumber;
    private final LocalDateTime entryTime;

    public Ticket(int entryGateId, Vehicle vehicle, Slot slot, int floorNumber) {
        this.ticketId = ++counter;
        this.entryGateId = entryGateId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.floorNumber = floorNumber;
        this.entryTime = LocalDateTime.now();
    }

    public int getTicketId() { return ticketId; }
    public int getEntryGateId() { return entryGateId; }
    public Vehicle getVehicle() { return vehicle; }
    public Slot getSlot() { return slot; }
    public int getFloorNumber() { return floorNumber; }
    public LocalDateTime getEntryTime() { return entryTime; }

    @Override
    public String toString() {
        return "Ticket #" + ticketId
            + " | Vehicle: " + vehicle
            + " | Floor: " + floorNumber
            + " | Slot: " + slot.getSlotId()
            + " | Entry: " + entryTime.format(FMT)
            + " | Gate: " + entryGateId;
    }
}
