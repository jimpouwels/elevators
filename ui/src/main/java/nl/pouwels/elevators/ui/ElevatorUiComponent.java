package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.Elevator;
import nl.pouwels.elevators.hardware.PhysicalElevator;
import nl.pouwels.gameengine.Drawable;
import nl.pouwels.gameengine.Engine;

public class ElevatorUiComponent implements PhysicalElevator, Drawable {

    private final Engine engine;
    private Elevator elevator;

    public ElevatorUiComponent(Engine engine, int offSetX, int offSetY) {
        this.engine = engine;
    }

    @Override
    public void subscribe(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void draw(long currenTime) {
    }
}
