package nl.pouwels.elevators.ui;

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
    private Engine engine;

    public void runGame() {
        engine = new Engine(WIDTH, HEIGHT, PIXEL_SIZE, FPS, this);
        for (int i = 0; i < 3; i++) {
            ElevatorUiComponent physicalElevator = new ElevatorUiComponent(engine, i * 40, 0);
            elevators.add(new Elevator(physicalElevator));
        }
        engine.startGameLoop();
    }

    @Override
    public void handleFrame(long elapsedTime, long currentTime) {
        resetScreen();
        elevators.forEach(e -> ((ElevatorUiComponent) e.getPhysicalElevator()).draw(currentTime));
    }

    @Override
    public void onKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                elevators.forEach(Elevator::open);
                break;
            case KeyEvent.VK_DOWN:
                elevators.forEach(Elevator::close);
                break;
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
    }

    private void resetScreen() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                engine.drawPixel(x, y, Color.BLACK);
            }
        }
    }
}
