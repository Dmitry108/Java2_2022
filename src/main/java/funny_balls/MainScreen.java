package funny_balls;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String TITLE = "Funny balls";

    private final FunnyBall[] balls = new FunnyBall[10];

    public MainScreen() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        BallsCanvas ballsCanvas = new BallsCanvas(this);
        add(ballsCanvas);
        init();
        setVisible(true);
    }

    private void init() {
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new FunnyBall();
        }
    }

    private void update(BallsCanvas canvas, float deltaTime) {
        for (FunnyBall ball : balls) {
            ball.update(canvas, deltaTime);
        }
    }

    private void render(BallsCanvas canvas, Graphics graphics) {
        for (FunnyBall ball : balls) {
            ball.render(canvas, graphics);
        }
    }

    public void onDrawCanvas(BallsCanvas canvas, Graphics graphics, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, graphics);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}