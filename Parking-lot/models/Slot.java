package models;

public class Slot {
    private final int slotId;
    private final SlotType slotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public Slot(int slotId, SlotType slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public void park(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void unpark() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public int getSlotId() {
        return slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    @Override
    public String toString() {
        if (isOccupied) {
            return "Slot-" + slotId + " (" + slotType + ") -> " + parkedVehicle;
        }
        return "Slot-" + slotId + " (" + slotType + ") -> EMPTY";
    }
}
