package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.FloorButton;
import nl.pouwels.elevators.hardware.PhysicalFloorButton;
import nl.pouwels.gameengine.Engine;

public class FloorButtonUiComponent extends ClickableUiComponent implements PhysicalFloorButton {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private FloorButton floorButton;

    public FloorButtonUiComponent(Engine engine, int x, int y) {
        super(engine, x, y, WIDTH, HEIGHT);
    }

    @Override
    public void subscribe(FloorButton floorButton) {
        this.floorButton = floorButton;
    }

    @Override
    public void onClick(int x, int y) {
    }

    @Override
    public void draw(long currenTime) {

    }
}
