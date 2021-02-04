package nl.pouwels.elevators;

import nl.pouwels.elevators.hardware.PhysicalDoor;

import java.time.Instant;

public class Door {

    private static final int DOOR_OPEN_TIME_IN_MILLIS = 3000;
    private final PhysicalDoor physicalDoor;
    private FloorButton button;
    private DoorStatus doorStatus = DoorStatus.CLOSED;

    public Door(PhysicalDoor physicalDoor, FloorButton button) {
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
        if (doorStatus == DoorStatus.OPEN) {
            long openTime = Instant.now().toEpochMilli();
            Runnable runnable = () -> {
                while ((Instant.now().toEpochMilli() - openTime) <= DOOR_OPEN_TIME_IN_MILLIS) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                close();
            };
            runnable.run();
        }
    }
}
