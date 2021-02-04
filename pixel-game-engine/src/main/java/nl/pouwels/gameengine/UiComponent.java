package nl.pouwels.gameengine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public abstract class UiComponent implements Drawable {

    private Engine engine;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final List<UiComponent> children = new ArrayList<>();
    private final UiComponent parent;

    public UiComponent(Engine engine, UiComponent parent, int x, int y, int width, int height) {
        this.engine = engine;
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public UiComponent(Engine engine, int x, int y, int width, int height) {
        this(engine, null, x, y, width, height);
    }

    public void drawPixel(int x, int y, Color color) {
        engine.drawPixel(x, y, color);
    }

    public abstract int getFullWidth();

    public abstract int getFullHeight();

    public abstract void onChildClicked(UiComponent uiComponent);

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<UiComponent> getChildren() {
        return children;
    }

    public void addChild(UiComponent child) {
        children.add(child);
    }

    public UiComponent getParent() {
        return parent;
    }
}
