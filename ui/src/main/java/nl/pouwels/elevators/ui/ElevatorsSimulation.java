package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.Elevator;
import nl.pouwels.gameengine.Color;
import nl.pouwels.gameengine.Engine;
import nl.pouwels.gameengine.FrameHandler;

import java.awt.event.KeyEvent;

public class ElevatorsSimulation implements FrameHandler {

    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;
    private static final int PIXEL_SIZE = 3;
    private static final int FPS = 60;
    private static Engine engine;
    private Elevator elevator;

    public void runGame() {
        engine = new Engine(WIDTH, HEIGHT, PIXEL_SIZE, FPS, this);
        ElevatorUiComponent physicalElevator = new ElevatorUiComponent(engine);
        elevator = new Elevator(physicalElevator);
        engine.startGameLoop();
    }

    @Override
    public void handleFrame(long elapsedTime, long currentTime) {
        resetScreen();
        ((ElevatorUiComponent) elevator.getPhysicalElevator()).draw(currentTime);
    }

    @Override
    public void onKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                elevator.open();
                break;
            case KeyEvent.VK_DOWN:
                elevator.close();
                break;
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
    }

    private void resetScreen() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                engine.drawPixel(x, y, new Color(0, 0, 0));
            }
        }
    }
}
