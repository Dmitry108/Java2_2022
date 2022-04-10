package funny_balls;

import java.awt.*;
import java.security.SecureRandom;

public class FunnyBall extends Sprite {
    private static final SecureRandom random = new SecureRandom();
    private final Color color;
    private float vX;
    private float vY;

    public FunnyBall() {
        halfWidth = halfHeight = 20 + random.nextFloat(50);
        color = new Color(random.nextInt());
        vX = 100f + random.nextFloat(200f);
        vY = 100f + random.nextFloat(200f);
    }

    @Override
    public void update(SpritesCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX *= -1;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX *= -1;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY *= -1;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY *= -1;
        }
    }

    @Override
    public void render(SpritesCanvas canvas, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }
}