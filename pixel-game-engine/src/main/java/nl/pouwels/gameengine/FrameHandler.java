package nl.pouwels.gameengine;

public interface FrameHandler {

    void handleFrame(long elapsedTime, long currentTime);

    void onKeyPressed(int keyCode);

    void onMouseClicked(int x, int y);
}
