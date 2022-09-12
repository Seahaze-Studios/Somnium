package graphics.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public interface UIElement {

    public abstract void render(GameContainer gc);
    public abstract void update(GameContainer gc);

}
