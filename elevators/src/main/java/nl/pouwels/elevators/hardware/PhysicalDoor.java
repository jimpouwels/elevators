package nl.pouwels.elevators.hardware;

import nl.pouwels.elevators.Door;

public interface PhysicalDoor {
    void subscribe(Door door);

    void open();

    void close();
}
