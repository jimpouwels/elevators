package nl.pouwels.elevators;

import nl.pouwels.elevators.hardware.PhysicalFloorButton;

public class Button {
    private PhysicalFloorButton physicalFloorButton;

    public Button(PhysicalFloorButton physicalFloorButton) {
        this.physicalFloorButton = physicalFloorButton;
    }
}
