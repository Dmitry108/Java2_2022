package funny_balls;

import javax.swing.JPanel;
import java.awt.*;

public class BallsCanvas extends JPanel {

    private long lastFrameTime;
    private final MainScreen mainController;

    BallsCanvas(MainScreen controller) {
        this.lastFrameTime = System.nanoTime();
        this.mainController = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        mainController.onDrawCanvas(this, g, deltaTime);
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public int getLeft() {
        return 0;
    }

    public int getRight() {
        return getWidth() - 1;
    }

    public int getTop() {
        return 0;
    }

    public int getBottom() {
        return getHeight() - 1;
    }
}