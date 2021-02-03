package nl.pouwels.elevators;

import nl.pouwels.elevators.hardware.PhysicalDoor;

public class Door {

    private final PhysicalDoor physicalDoor;

    private DoorStatus doorStatus = DoorStatus.CLOSED;

    public PhysicalDoor getPhysicalDoor() {
        return physicalDoor;
    }

    public Door(PhysicalDoor physicalDoor) {
        this.physicalDoor = physicalDoor;
        physicalDoor.subscribe(this);
    }

    public void open() {
        if (doorStatus == DoorStatus.CLOSED) {
            doorStatus = DoorStatus.OPENING;
            physicalDoor.open();
        }
    }

    public void close() {
        if (doorStatus == DoorStatus.OPEN) {
            doorStatus = DoorStatus.CLOSING;
            physicalDoor.close();
        }
    }

    public boolean isOpen() {
        return doorStatus == DoorStatus.OPEN;
    }

    public boolean isClosing() {
        return doorStatus == DoorStatus.CLOSING;
    }

    public boolean isOpening() {
        return doorStatus == DoorStatus.OPENING;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void onDoorStatusChanged(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }
}
