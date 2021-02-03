package nl.pouwels.elevators.ui;

import nl.pouwels.gameengine.Drawable;
import nl.pouwels.gameengine.Engine;

import java.awt.*;

public abstract class UiComponent implements Drawable {

    private Engine engine;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public UiComponent(Engine engine, int x, int y, int width, int height) {
        this.engine = engine;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawPixel(int x, int y, Color color) {
        engine.drawPixel(x, y, color);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
