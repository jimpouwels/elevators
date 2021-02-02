package nl.pouwels.elevators;

import nl.pouwels.elevators.ui.Color;
import nl.pouwels.elevators.ui.Engine;

import java.time.Instant;

public class ElevatorUiComponent implements PhysicalElevator {

    private static final double MILLIS_TO_OPEN_OR_CLOSE = 1000;
    private static final int DOOR_HEIGHT = 100;
    private static final int DOOR_WIDTH = 100;
    private long timer;
    private Engine engine;
    private Elevator elevator;

    public ElevatorUiComponent(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void subscribe(Elevator elevator) {
        this.elevator = elevator;
    }

    public void draw(long currentTime) {
        drawDoors(engine, currentTime);
    }

    @Override
    public void open() {
        this.timer = Instant.now().toEpochMilli();
    }

    @Override
    public void close() {
        this.timer = Instant.now().toEpochMilli();
    }

    private void drawDoors(Engine engine, long currentTime) {
        int doorWidth = getDoorWidthBasedOnState();
        if (elevator.isOpening() || elevator.isClosing()) {
            long elapsedTimeSinceTimer = currentTime - timer;

            double doorStateChangePercentageCompleted = elapsedTimeSinceTimer / MILLIS_TO_OPEN_OR_CLOSE;
            if (doorStateChangePercentageCompleted >= 1) {
                elevator.onDoorStatusChanged(elevator.isOpening() ? DoorStatus.OPEN : DoorStatus.CLOSED);
                doorWidth = getDoorWidthBasedOnState();
            } else {
                if (elevator.isOpening()) {
                    doorWidth = (int) ((DOOR_WIDTH) * (1 - doorStateChangePercentageCompleted));
                } else if (elevator.isClosing()) {
                    doorWidth = (int) ((DOOR_WIDTH) * doorStateChangePercentageCompleted);

                }
            }
        }
        drawLeftDoor(engine, doorWidth);
        drawRightDoor(engine, doorWidth);
    }

    private int getDoorWidthBasedOnState() {
        return elevator.isOpen() ? 0 : DOOR_WIDTH;
    }

    private void drawLeftDoor(Engine engine, int doorWidth) {
        for (int x = 0; x < doorWidth; x++) {
            for (int y = 0; y < DOOR_HEIGHT; y++) {
                engine.drawPixel(x, y, new Color(0, 190, 0));
            }
        }
    }

    private void drawRightDoor(Engine engine, int doorWidth) {
        for (int x = 0; x < doorWidth; x++) {
            for (int y = 0; y < DOOR_HEIGHT; y++) {
                engine.drawPixel(x + DOOR_WIDTH + (DOOR_WIDTH - doorWidth), y, new Color(0, 190, 0));
            }
        }
    }
}
