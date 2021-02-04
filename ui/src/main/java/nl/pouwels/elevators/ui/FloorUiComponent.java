package nl.pouwels.elevators.ui;

import nl.pouwels.gameengine.Engine;
import nl.pouwels.gameengine.UiComponent;

public class FloorUiComponent extends UiComponent {

    public FloorUiComponent(Engine engine, UiComponent parent, int x, int y) {
        super(engine, parent, x, y, 0, 0);
    }

    @Override
    public void draw(long currenTime) {
    }

    @Override
    public int getFullWidth() {
        return 0;
    }

    @Override
    public int getFullHeight() {
        return 0;
    }

    @Override
    public void onChildClicked(UiComponent uiComponent) {
    }
}
