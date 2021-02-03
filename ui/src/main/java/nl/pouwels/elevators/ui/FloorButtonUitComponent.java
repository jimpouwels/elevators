package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.FloorButton;
import nl.pouwels.elevators.hardware.PhysicalFloorButton;
import nl.pouwels.gameengine.ClickableUiComponent;

public class FloorButtonUitComponent implements PhysicalFloorButton, ClickableUiComponent {

    private FloorButton floorButton;

    @Override
    public void subscribe(FloorButton floorButton) {
        this.floorButton = floorButton;
    }

    @Override
    public boolean isClicked(int x, int y) {
        return false;
    }
}
