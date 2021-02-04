package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.Door;
import nl.pouwels.elevators.DoorStatus;
import nl.pouwels.elevators.hardware.PhysicalDoor;
import nl.pouwels.gameengine.Engine;
import nl.pouwels.gameengine.UiComponent;

import java.awt.*;
import java.time.Instant;

public class DoorUiComponent extends UiComponent implements PhysicalDoor {
    public static final int DOOR_HEIGHT = 50;
    public static final int DOOR_WIDTH = 12;
    private static final double MILLIS_TO_OPEN_OR_CLOSE = 1000;
    private long timer;
    private Door door;

    public DoorUiComponent(Engine engine, int x, int y) {
        super(engine, x, y, DOOR_WIDTH * 2, DOOR_HEIGHT);
    }

    public void subscribe(Door door) {
        this.door = door;
    }

    @Override
    public void draw(long currentTime) {
        drawDoors(currentTime);
        for (UiComponent child : getChildren()) {
            child.draw(currentTime);
        }
    }

    @Override
    public void open() {
        this.timer = Instant.now().toEpochMilli();
    }

    @Override
    public void close() {
        this.timer = Instant.now().toEpochMilli();
    }

    @Override
    public int getFullWidth() {
        return DOOR_WIDTH * 2;
    }

    @Override
    public int getFullHeight() {
        return DOOR_HEIGHT;
    }

    @Override
    public void onChildClicked(UiComponent uiComponent) {
        door.open();
    }

    private void drawDoors(long currentTime) {
        int doorWidth = getDoorWidthBasedOnState(currentTime);
        drawLeftDoor(doorWidth);
        drawRightDoor(doorWidth);
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

    private void drawLeftDoor(int doorWidth) {
        drawDoor(doorWidth, 0);
    }

    private void drawRightDoor(int doorWidth) {
        int rightDoorOffsetX = DOOR_WIDTH + (DOOR_WIDTH - doorWidth);
        drawDoor(doorWidth, rightDoorOffsetX);
    }

    private void drawDoor(int doorWidth, int doorOffsetX) {
        for (int x = 0; x < doorWidth; x++) {
            for (int y = 0; y < DOOR_HEIGHT; y++) {
                drawPixel(getPositionX() + doorOffsetX + x, getPositionY() + y, Color.GRAY);
            }
        }
    }
}