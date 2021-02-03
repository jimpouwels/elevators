package nl.pouwels.elevators;

import nl.pouwels.elevators.hardware.PhysicalFloorButton;

public class FloorButton {

    private final PhysicalFloorButton physicalFloorButton;

    public FloorButton(PhysicalFloorButton physicalFloorButton) {
        this.physicalFloorButton = physicalFloorButton;
        physicalFloorButton.subscribe(this);
    }
}
