package nl.pouwels.elevators;

import nl.pouwels.elevators.hardware.PhysicalElevator;

public class Elevator {

    private final PhysicalElevator physicalElevator;

    public Elevator(PhysicalElevator physicalElevator) {
        this.physicalElevator = physicalElevator;
        physicalElevator.subscribe(this);
    }

    public PhysicalElevator getPhysicalElevator() {
        return physicalElevator;
    }

}
