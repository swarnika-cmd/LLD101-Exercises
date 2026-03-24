import models.*;
import gates.*;
import strategy.*;
import java.util.*;

public class ParkingLot {
    private final String name;
    private final List<Floor> floors;
    private final Map<Integer, EntryGate> entryGates;
    private final Map<Integer, ExitGate> exitGates;
    private final Map<Integer, Ticket> activeTickets;

    public ParkingLot(String name, List<Floor> floors,
                      List<EntryGate> entryGates, List<ExitGate> exitGates) {
        this.name = name;
        this.floors = floors;
        this.entryGates = new HashMap<>();
        this.exitGates = new HashMap<>();
        this.activeTickets = new HashMap<>();

        for (EntryGate g : entryGates) {
            this.entryGates.put(g.getGateId(), g);
        }
        for (ExitGate g : exitGates) {
            this.exitGates.put(g.getGateId(), g);
        }
    }

    public Ticket park(Vehicle vehicle, int entryGateId) {
        EntryGate gate = entryGates.get(entryGateId);
        if (gate == null) {
            return null;
        }
        Ticket ticket = gate.issueTicket(vehicle, floors);
        if (ticket != null) {
            activeTickets.put(ticket.getTicketId(), ticket);
        }
        return ticket;
    }

    public Bill unpark(int ticketId, int exitGateId) {
        ExitGate gate = exitGates.get(exitGateId);
        Ticket ticket = activeTickets.get(ticketId);
        if (gate == null || ticket == null) {
            return null;
        }
        activeTickets.remove(ticketId);
        return gate.processExit(ticket);
    }

    public String getName() { return name; }
    public List<Floor> getFloors() { return floors; }
    public int getActiveTicketCount() { return activeTickets.size(); }

    @Override
    public String toString() {
        return name + " | Floors: " + floors.size()
            + " | Entry gates: " + entryGates.size()
            + " | Exit gates: " + exitGates.size()
            + " | Active: " + activeTickets.size();
    }
}
