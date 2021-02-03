package nl.pouwels.elevators.hardware;

import nl.pouwels.elevators.Elevator;

public interface PhysicalElevator {

    void subscribe(Elevator elevator);
}
