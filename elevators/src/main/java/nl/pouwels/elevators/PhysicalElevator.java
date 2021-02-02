package nl.pouwels.elevators;

public interface PhysicalElevator {

    void subscribe(Elevator elevator);

    void draw(long currentTime);

    void open();

    void close();
}
