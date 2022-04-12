package funny_sprites.funny_balls;

import funny_sprites.funny_common.ISpritesController;
import funny_sprites.funny_common.ITimeChangeable;
import funny_sprites.funny_common.Sprite;
import funny_sprites.funny_common.SpritesCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFunnyBalls extends JFrame implements ISpritesController, MouseListener {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String TITLE = "Funny balls";

    private ITimeChangeable[] funnyEntities = new ITimeChangeable[1];
    private int entityCount;

    public MainFunnyBalls() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        SpritesCanvas canvas = new SpritesCanvas(this);
        canvas.addMouseListener(this);
        add(canvas);
        init();
        setVisible(true);
    }

    private void init() {
        addEntity(new Background());
    }

    private void update(SpritesCanvas canvas, float deltaTime) {
        for (int i = 0; i < entityCount; i++) {
            funnyEntities[i].update(canvas, deltaTime);
        }
    }

    private void render(SpritesCanvas canvas, Graphics graphics) {
        for (int i = 0; i < entityCount; i++) {
            funnyEntities[i].render(canvas, graphics);
        }
    }

    @Override
    public void onDrawCanvas(SpritesCanvas canvas, Graphics graphics, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, graphics);
    }

    public void addEntity(ITimeChangeable entity) {
        if (funnyEntities.length == entityCount) {
            ITimeChangeable[] oldArray = funnyEntities;
            funnyEntities = new ITimeChangeable[oldArray.length * 2];
            System.arraycopy(oldArray, 0, funnyEntities, 0, oldArray.length);
        }
        funnyEntities[entityCount++] = entity;
        updateTitle();
    }

    public void removeEntity() {
        if (entityCount > 1) {
            funnyEntities[--entityCount] = null;
            updateTitle();
        }
    }

    private void updateTitle() {
        setTitle(TITLE + ": " + (entityCount - 1));
    }

    public static void main(String[] args) {
        new MainFunnyBalls();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (mouseEvent.getButton()) {
            case MouseEvent.BUTTON1 -> addEntity(new FunnyBall());
            case MouseEvent.BUTTON3 -> removeEntity();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) { }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }
}