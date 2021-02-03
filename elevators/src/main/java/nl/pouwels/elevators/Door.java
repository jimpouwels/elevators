package nl.pouwels.elevators;

import nl.pouwels.elevators.hardware.PhysicalDoor;

import java.awt.*;

public class Door {

    private final PhysicalDoor physicalDoor;
    private Button button;
    private DoorStatus doorStatus = DoorStatus.CLOSED;

    public Door(PhysicalDoor physicalDoor, Button button) {
        this.physicalDoor = physicalDoor;
        this.button = button;
        physicalDoor.subscribe(this);
    }

    public PhysicalDoor getPhysicalDoor() {
        return physicalDoor;
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
