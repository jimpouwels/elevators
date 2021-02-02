package nl.pouwels.elevators.ui;

public interface FrameHandler {

    void handleFrame(long elapsedTime, long currentTime);

    void onKeyPressed(int keyCode);

    void onKeyReleased(int keyCode);
}
