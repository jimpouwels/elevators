package nl.pouwels.elevators.ui;

import nl.pouwels.gameengine.Engine;

public abstract class ClickableUiComponent extends UiComponent {

    public ClickableUiComponent(Engine engine, int x, int y, int width, int height) {
        super(engine, x, y, width, height);
    }

    public boolean isClicked(int x, int y) {
        return x >= this.getX() && x <= this.getX() + this.getWidth()
                && y >= this.getY() && y <= this.getY() + this.getHeight();
    }

    public abstract void onClick(int x, int y);
}
