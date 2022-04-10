package funny_balls;

import java.awt.*;

public class Sprite {
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;

    protected float getLeft() {
        return x - halfWidth;
    }

    protected void setLeft(float left) {
        x = left + halfWidth;
    }

    protected float getRight() {
        return x + halfWidth;
    }

    protected void setRight(float right) {
        x = right - halfWidth;
    }

    protected float getTop() {
        return y - halfHeight;
    }

    protected void setTop(float top) {
        y = top + halfHeight;
    }

    protected float getBottom() {
        return y + halfHeight;
    }

    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }

    protected float getWidth() {
        return halfWidth * 2f;
    }

    protected float getHeight() {
        return halfHeight * 2f;
    }

    public void update(SpritesCanvas canvas, float deltaTime) {

    }

    public void render(SpritesCanvas canvas, Graphics graphics) {

    }
}