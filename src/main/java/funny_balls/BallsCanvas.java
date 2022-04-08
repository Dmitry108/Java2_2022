package funny_balls;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BallsCanvas extends JPanel {

    private float totalFrameTime;
    private long lastFrameTime;
    private final MainScreen mainController;

    BallsCanvas(MainScreen controller) {
        this.totalFrameTime = 0;
        this.lastFrameTime = System.nanoTime();
        this.mainController = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> mainController.addBall();
                    case MouseEvent.BUTTON3 -> mainController.removeBall();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        totalFrameTime += deltaTime;
        lastFrameTime = currentTime;
        mainController.onDrawCanvas(this, g, deltaTime);
        setBackground(Background.getColorOfRainbowByLineFunction(totalFrameTime, 20));
//        setBackground(Background.getColorOfRainbowByCosFunction(totalFrameTime, 20));
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