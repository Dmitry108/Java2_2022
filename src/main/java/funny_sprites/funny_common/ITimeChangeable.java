package funny_sprites.funny_common;

import java.awt.*;

public interface ITimeChangeable {
    void update(SpritesCanvas canvas, float deltaTime);
    void render(SpritesCanvas canvas, Graphics graphics);
}