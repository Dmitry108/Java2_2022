package funny_balls;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String TITLE = "Funny balls";

    private FunnyBall[] balls = new FunnyBall[10];

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

    public void addBall() {
        FunnyBall[] oldArray = balls;
        balls = new FunnyBall[oldArray.length + 1];
        System.arraycopy(oldArray, 0, balls, 0, oldArray.length);
        balls[balls.length - 1] = new FunnyBall();
        setTitle(TITLE + " " + balls.length);
    }

    public void removeBall() {
        if (balls.length > 0) {
            FunnyBall[] oldArray = balls;
            balls = new FunnyBall[oldArray.length - 1];
            System.arraycopy(oldArray, 0, balls, 0, balls.length);
            setTitle(TITLE + " " + balls.length);
        }
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}