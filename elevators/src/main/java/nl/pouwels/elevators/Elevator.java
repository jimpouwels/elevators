package nl.pouwels.elevators;

public class Elevator {

    private boolean isOpen;
    private boolean isOpening;
    private boolean isClosing;
    private PhysicalElevator physicalElevator;

    public Elevator(PhysicalElevator physicalElevator) {
        this.physicalElevator = physicalElevator;
        physicalElevator.subscribe(this);
    }

    public PhysicalElevator getPhysicalElevator() {
        return physicalElevator;
    }

    public void open() {
        if (!(isOpen || isOpening)) {
            isOpening = true;
            physicalElevator.open();
        }
    }

    public void close() {
        if (isOpen && !isClosing) {
            isClosing = true;
            physicalElevator.close();
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isOpening() {
        return isOpening;
    }

    public boolean isClosing() {
        return isClosing;
    }

    public void onDoorOpened() {
        isOpen = true;
        isOpening = false;
    }

    public void onDoorClosed() {
        isOpen = false;
        isClosing = false;
    }
}
