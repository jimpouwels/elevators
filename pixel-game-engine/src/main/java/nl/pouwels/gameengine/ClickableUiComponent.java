package nl.pouwels.gameengine;

public abstract class ClickableUiComponent extends UiComponent {

    public ClickableUiComponent(Engine engine, UiComponent parent, int x, int y, int width, int height) {
        super(engine, parent, x, y, width, height);
    }

    public ClickableUiComponent(Engine engine, int x, int y, int width, int height) {
        super(engine, x, y, width, height);
    }

    public boolean isClicked(int x, int y) {
        return x >= this.getPositionX() && x <= (this.getPositionX() + this.getFullWidth())
                && y >= this.getPositionY() && y <= (this.getPositionY() + this.getFullHeight());
    }

    public void onClick(int x, int y) {
        getParent().onChildClicked(this);
    }
}
