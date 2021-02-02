package nl.pouwels.elevators;

public class Elevator {

    private final PhysicalElevator physicalElevator;
    private DoorStatus doorStatus = DoorStatus.CLOSED;

    public Elevator(PhysicalElevator physicalElevator) {
        this.physicalElevator = physicalElevator;
        physicalElevator.subscribe(this);
    }

    public PhysicalElevator getPhysicalElevator() {
        return physicalElevator;
    }

    public void open() {
        if (doorStatus == DoorStatus.CLOSED) {
            doorStatus = DoorStatus.OPENING;
            physicalElevator.open();
        }
    }

    public void close() {
        if (doorStatus == DoorStatus.OPEN) {
            doorStatus = DoorStatus.CLOSING;
            physicalElevator.close();
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
