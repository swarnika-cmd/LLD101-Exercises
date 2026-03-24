package gates;

import models.Bill;
import models.Ticket;

public class ExitGate {
    private final int gateId;

    public ExitGate(int gateId) {
        this.gateId = gateId;
    }

    public Bill processExit(Ticket ticket) {
        ticket.getSlot().unpark();
        return new Bill(ticket);
    }

    public int getGateId() { return gateId; }

    @Override
    public String toString() {
        return "ExitGate-" + gateId;
    }
}
