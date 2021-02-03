package nl.pouwels.elevators.hardware;

import nl.pouwels.elevators.FloorButton;

public interface PhysicalFloorButton {
    void subscribe(FloorButton floorButton);
}
