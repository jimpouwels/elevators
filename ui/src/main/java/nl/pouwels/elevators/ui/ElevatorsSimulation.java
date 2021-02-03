package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.Door;
import nl.pouwels.elevators.Elevator;
import nl.pouwels.gameengine.Engine;
import nl.pouwels.gameengine.FrameHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ElevatorsSimulation implements FrameHandler {

    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;
    private static final int PIXEL_SIZE = 3;
    private static final int FPS = 60;
    private final List<Elevator> elevators = new ArrayList<>();
    private final List<Door> doors = new ArrayList<>();
    private Engine engine;

    public void runGame() {
        engine = new Engine(WIDTH, HEIGHT, PIXEL_SIZE, FPS, this);
        renderFloor(3);
        engine.startGameLoop();
    }

    @Override
    public void handleFrame(long elapsedTime, long currentTime) {
        resetScreen();
        doors.forEach(e -> ((DoorUiComponent) e.getPhysicalDoor()).draw(currentTime));
    }

    @Override
    public void onKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                doors.forEach(Door::open);
                break;
            case KeyEvent.VK_DOWN:
                doors.forEach(Door::close);
                break;
        }
    }

    @Override
    public void onMouseClicked(int x, int y) {
        for (ClickableUiComponent clickableUiComponent : UiComponentRegistry.getClickableUiComponents()) {
            clickableUiComponent.isClicked(x - clickableUiComponent.getX(), y - clickableUiComponent.getY());
        }
    }

    private void resetScreen() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                engine.drawPixel(x, y, Color.BLACK);
            }
        }
    }

    private void renderFloor(int elevatorCount) {
        int margin = 10;
        int spaceBetweenElevators = (WIDTH - (margin * 2) - (elevatorCount * (DoorUiComponent.DOOR_WIDTH * 2))) / 4;
        int offsetX = margin + spaceBetweenElevators;
        for (int i = 0; i < elevatorCount; i++) {
            doors.add(EntityFactory.createDoor(engine, i + offsetX, margin));
            offsetX += spaceBetweenElevators + (DoorUiComponent.DOOR_WIDTH * 2);
        }
    }
}
