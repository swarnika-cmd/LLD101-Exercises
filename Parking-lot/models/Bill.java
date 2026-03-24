package models;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Bill {
    private static int counter = 0;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final double RATE_TWO_WHEELER = 1.0;   // per minute
    private static final double RATE_FOUR_WHEELER = 2.0;

    private final int billId;
    private final Ticket ticket;
    private final LocalDateTime exitTime;
    private final long durationMinutes;
    private final double amount;

    public Bill(Ticket ticket) {
        this.billId = ++counter;
        this.ticket = ticket;
        this.exitTime = LocalDateTime.now();
        this.durationMinutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        this.amount = calculateAmount();
    }

    private double calculateAmount() {
        // at least charge for 1 minute even if duration is 0
        long mins = Math.max(durationMinutes, 1);
        if (ticket.getVehicle().getVehicleType() == VehicleType.TWO_WHEELER) {
            return mins * RATE_TWO_WHEELER;
        }
        return mins * RATE_FOUR_WHEELER;
    }

    public int getBillId() { return billId; }
    public Ticket getTicket() { return ticket; }
    public LocalDateTime getExitTime() { return exitTime; }
    public double getAmount() { return amount; }

    public String generateReceipt() {
        return "--- RECEIPT ---"
            + "\nBill #" + billId
            + "\nVehicle: " + ticket.getVehicle()
            + "\nFloor: " + ticket.getFloorNumber() + " | Slot: " + ticket.getSlot().getSlotId()
            + "\nEntry: " + ticket.getEntryTime().format(FMT)
            + "\nExit:  " + exitTime.format(FMT)
            + "\nDuration: " + durationMinutes + " min"
            + "\nAmount: Rs " + String.format("%.2f", amount)
            + "\n---------------";
    }

    @Override
    public String toString() {
        return "Bill #" + billId + " | Amount: Rs " + String.format("%.2f", amount);
    }
}
