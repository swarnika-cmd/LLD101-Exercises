package models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Slot> slots;

    public Floor(int floorNumber, List<Slot> slots) {
        this.floorNumber = floorNumber;
        this.slots = slots;
    }

    public List<Slot> getAvailableSlots(SlotType type) {
        List<Slot> available = new ArrayList<>();
        for (Slot s : slots) {
            if (s.isAvailable() && s.getSlotType() == type) {
                available.add(s);
            }
        }
        return available;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    @Override
    public String toString() {
        return "Floor-" + floorNumber + " (" + slots.size() + " slots)";
    }
}
