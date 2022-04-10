package funny_balls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends JFrame {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String TITLE = "Funny balls";

    private Sprite[] sprites = new Sprite[1];
    private int spritesCount;

    public MainScreen() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        SpritesCanvas canvas = new SpritesCanvas(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> addSprite(new FunnyBall());
                    case MouseEvent.BUTTON3 -> removeSprite();
                }
            }
        });
        add(canvas);
        init();
        setVisible(true);
    }

    private void init() {
        addSprite(new Background());
    }

    private void update(SpritesCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(SpritesCanvas canvas, Graphics graphics) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(canvas, graphics);
        }
    }

    public void onDrawCanvas(SpritesCanvas canvas, Graphics graphics, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, graphics);
    }

    public void addSprite(Sprite sprite) {
        if (sprites.length == spritesCount) {
            Sprite[] oldArray = sprites;
            sprites = new Sprite[oldArray.length * 2];
            System.arraycopy(oldArray, 0, sprites, 0, oldArray.length);
        }
        sprites[spritesCount++] = sprite;
        updateTitle();
    }

    public void removeSprite() {
        if (spritesCount > 1) {
            sprites[--spritesCount] = null;
            updateTitle();
        }
    }

    private void updateTitle() {
        setTitle(TITLE + ": " + (spritesCount - 1));
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}