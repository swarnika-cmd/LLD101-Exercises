package strategy;

import models.Slot;
import models.SlotType;
import models.Floor;
import models.VehicleType;
import java.util.List;

public class NearestFirstStrategy implements SlotAllocationStrategy {

    @Override
    public Slot findSlot(List<Floor> floors, VehicleType vehicleType) {
        SlotType needed = mapVehicleToSlot(vehicleType);

        // go floor by floor (lowest first = nearest to entrance)
        for (Floor floor : floors) {
            List<Slot> available = floor.getAvailableSlots(needed);
            if (!available.isEmpty()) {
                return available.get(0);  // first available on this floor
            }
        }
        return null; // lot is full for this type
    }

    private SlotType mapVehicleToSlot(VehicleType vt) {
        switch (vt) {
            case TWO_WHEELER:  return SlotType.TWO_WHEELER;
            case FOUR_WHEELER: return SlotType.FOUR_WHEELER;
            default:           return SlotType.FOUR_WHEELER;
        }
    }
}
