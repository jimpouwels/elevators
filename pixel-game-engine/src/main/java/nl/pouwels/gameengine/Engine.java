package nl.pouwels.gameengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.time.Instant;

public class Engine extends JPanel implements KeyListener, MouseListener {

    private final BufferedImage gameCanvas;
    private final int millisPerFrame;
    private final int width;
    private final int height;
    private final int pixelSize;
    private long previousFrameTime;
    private long startTime;
    private FrameHandler frameHandler;

    public Engine(int width, int height, int pixelSize, int fps, FrameHandler frameHandler) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        this.millisPerFrame = 1000 / fps;
        this.frameHandler = frameHandler;
        renderWindow();
        addKeyListener(this);
        setFocusable(true);
        gameCanvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        addMouseListener(this);
    }

    public void startGameLoop() {
        setVisible(true);
        startTime = Instant.now().toEpochMilli();
        Runnable runnable = () -> {
            do {
                long now = Instant.now().toEpochMilli();
                if (now - previousFrameTime >= millisPerFrame) {
                    frameHandler.handleFrame(now - startTime, now);
                    previousFrameTime = now;
                    repaint();
                }
            } while (true);
        };
        new Thread(runnable).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform imageSpaceTran = new AffineTransform();
        imageSpaceTran.scale(pixelSize, pixelSize);
        g2.drawImage(gameCanvas, imageSpaceTran, null);
    }

    public void drawPixel(int x, int y, java.awt.Color color) {
        drawPixel(gameCanvas, x, y, new Color(color.getRed(), color.getBlue(), color.getGreen()));
    }

    private void drawPixel(BufferedImage canvas, int x, int y, nl.pouwels.gameengine.Color color) {
        int rgb = toRgb(color);
        canvas.setRGB(x, y, rgb);
    }

    private int toRgb(Color color) {
        return ((color.r & 0x0ff) << 16) | ((color.g & 0x0ff) << 8) | (color.b & 0x0ff);
    }

    private void renderWindow() {
        JFrame frame = new JFrame("Elevators");
        frame.setPreferredSize(new Dimension(width * pixelSize, height * pixelSize));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        frameHandler.onKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frameHandler.onMouseClicked(e.getX() / pixelSize, e.getY() / pixelSize);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
