package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.Door;
import nl.pouwels.elevators.DoorStatus;
import nl.pouwels.elevators.hardware.PhysicalDoor;
import nl.pouwels.gameengine.Drawable;
import nl.pouwels.gameengine.Engine;

import java.awt.*;
import java.time.Instant;

public class DoorUiComponent implements PhysicalDoor, Drawable {
    public static final int DOOR_HEIGHT = 50;
    public static final int DOOR_WIDTH = 12;
    private static final double MILLIS_TO_OPEN_OR_CLOSE = 1000;
    private final Engine engine;
    private final int offSetX;
    private final int offSetY;
    private long timer;
    private Door door;

    public DoorUiComponent(Engine engine, int offSetX, int offSetY) {
        this.engine = engine;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
    }

    public void subscribe(Door door) {
        this.door = door;
    }

    @Override
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
        if (door.isOpening() || door.isClosing()) {
            doorWidth = getWidthForOpeningOrClosingDoor(currentTime);
        }
        return doorWidth;
    }

    private int getWidthForOpenOrClosedDoor() {
        return door.isOpen() ? 0 : DOOR_WIDTH;
    }

    private int getWidthForOpeningOrClosingDoor(long currentTime) {
        int doorWidth = -1;
        long elapsedTimeSinceTimer = currentTime - timer;
        double doorStateChangePercentageCompleted = elapsedTimeSinceTimer / MILLIS_TO_OPEN_OR_CLOSE;
        if (doorStateChangePercentageCompleted >= 1) {
            door.onDoorStatusChanged(door.isOpening() ? DoorStatus.OPEN : DoorStatus.CLOSED);
            doorWidth = getWidthForOpenOrClosedDoor();
        } else {
            if (door.isOpening()) {
                doorWidth = (int) ((DOOR_WIDTH) * (1 - doorStateChangePercentageCompleted));
            } else if (door.isClosing()) {
                doorWidth = (int) ((DOOR_WIDTH) * doorStateChangePercentageCompleted);

            }
        }
        return doorWidth;
    }

    private void drawLeftDoor(Engine engine, int doorWidth) {
        drawDoor(engine, doorWidth, 0);
    }

    private void drawRightDoor(Engine engine, int doorWidth) {
        int rightDoorOffsetX = DOOR_WIDTH + (DOOR_WIDTH - doorWidth);
        drawDoor(engine, doorWidth, rightDoorOffsetX);
    }

    private void drawDoor(Engine engine, int doorWidth, int rightDoorOffsetX) {
        for (int x = 0; x < doorWidth; x++) {
            for (int y = 0; y < DOOR_HEIGHT; y++) {
                engine.drawPixel(x + rightDoorOffsetX + offSetX, y + offSetY, Color.GRAY);
            }
        }
    }
}