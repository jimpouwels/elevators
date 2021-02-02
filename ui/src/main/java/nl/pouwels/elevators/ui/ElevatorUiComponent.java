package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.DoorStatus;
import nl.pouwels.elevators.Elevator;
import nl.pouwels.elevators.PhysicalElevator;
import nl.pouwels.gameengine.Color;
import nl.pouwels.gameengine.Drawable;
import nl.pouwels.gameengine.Engine;

import java.time.Instant;

public class ElevatorUiComponent implements PhysicalElevator, Drawable {

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
        int doorWidth = getDoorWidthBasedOnState(currentTime);
        drawLeftDoor(engine, doorWidth);
        drawRightDoor(engine, doorWidth);
    }

    private int getDoorWidthBasedOnState(long currentTime) {
        int doorWidth = getWidthForOpenOrClosedDoor();
        if (elevator.isOpening() || elevator.isClosing()) {
            doorWidth = getWidthForOpeningOrClosingDoor(currentTime);
        }
        return doorWidth;
    }

    private int getWidthForOpenOrClosedDoor() {
        return elevator.isOpen() ? 0 : DOOR_WIDTH;
    }

    private int getWidthForOpeningOrClosingDoor(long currentTime) {
        int doorWidth = -1;
        long elapsedTimeSinceTimer = currentTime - timer;
        double doorStateChangePercentageCompleted = elapsedTimeSinceTimer / MILLIS_TO_OPEN_OR_CLOSE;
        if (doorStateChangePercentageCompleted >= 1) {
            elevator.onDoorStatusChanged(elevator.isOpening() ? DoorStatus.OPEN : DoorStatus.CLOSED);
            doorWidth = getWidthForOpenOrClosedDoor();
        } else {
            if (elevator.isOpening()) {
                doorWidth = (int) ((DOOR_WIDTH) * (1 - doorStateChangePercentageCompleted));
            } else if (elevator.isClosing()) {
                doorWidth = (int) ((DOOR_WIDTH) * doorStateChangePercentageCompleted);

            }
        }
        return doorWidth;
    }

    private void drawLeftDoor(Engine engine, int doorWidth) {
        drawDoor(engine, doorWidth, 0);
    }

    private void drawRightDoor(Engine engine, int doorWidth) {
        int offsetX = DOOR_WIDTH + (DOOR_WIDTH - doorWidth);
        drawDoor(engine, doorWidth, offsetX);
    }

    private void drawDoor(Engine engine, int doorWidth, int offsetX) {
        for (int x = 0; x < doorWidth; x++) {
            for (int y = 0; y < DOOR_HEIGHT; y++) {
                engine.drawPixel(x + offsetX, y, new Color(0, 190, 0));
            }
        }
    }
}
