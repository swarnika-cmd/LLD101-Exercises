package gates;

import models.*;
import strategy.SlotAllocationStrategy;
import java.util.List;

public class EntryGate {
    private final int gateId;
    private final SlotAllocationStrategy strategy;

    public EntryGate(int gateId, SlotAllocationStrategy strategy) {
        this.gateId = gateId;
        this.strategy = strategy;
    }

    public Ticket issueTicket(Vehicle vehicle, List<Floor> floors) {
        Slot slot = strategy.findSlot(floors, vehicle.getVehicleType());
        if (slot == null) {
            return null; // no slot available
        }
        slot.park(vehicle);
        int floorNum = findFloorNumber(slot, floors);
        return new Ticket(gateId, vehicle, slot, floorNum);
    }

    private int findFloorNumber(Slot slot, List<Floor> floors) {
        for (Floor f : floors) {
            if (f.getSlots().contains(slot)) {
                return f.getFloorNumber();
            }
        }
        return -1;
    }

    public int getGateId() { return gateId; }

    @Override
    public String toString() {
        return "EntryGate-" + gateId;
    }
}
