package nl.pouwels.elevators;

public interface PhysicalElevator {

    void subscribe(Elevator elevator);

    void open();

    void close();
}
