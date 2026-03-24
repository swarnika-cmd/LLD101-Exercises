package strategy;

import models.Slot;
import models.Floor;
import models.VehicleType;
import java.util.List;

// any new allocation strategy just implements this
public interface SlotAllocationStrategy {
    Slot findSlot(List<Floor> floors, VehicleType vehicleType);
}
