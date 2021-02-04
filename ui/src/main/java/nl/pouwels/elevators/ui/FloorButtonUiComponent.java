package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.FloorButton;
import nl.pouwels.elevators.hardware.PhysicalFloorButton;
import nl.pouwels.gameengine.ClickableUiComponent;
import nl.pouwels.gameengine.Engine;
import nl.pouwels.gameengine.UiComponent;

import java.awt.*;

public class FloorButtonUiComponent extends ClickableUiComponent implements PhysicalFloorButton {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private FloorButton floorButton;

    public FloorButtonUiComponent(Engine engine, UiComponent parent, int positionX, int positionY) {
        super(engine, parent, positionX + parent.getFullWidth() + 3, positionY + 16, WIDTH, HEIGHT);
    }

    @Override
    public void subscribe(FloorButton floorButton) {
        this.floorButton = floorButton;
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
    }

    @Override
    public void draw(long currenTime) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                drawPixel(getPositionX() + x, getPositionY() + y, Color.RED);
            }
        }
    }

    @Override
    public int getFullWidth() {
        return WIDTH;
    }

    @Override
    public int getFullHeight() {
        return HEIGHT;
    }

    @Override
    public void onChildClicked(UiComponent uiComponent) {
    }
}
