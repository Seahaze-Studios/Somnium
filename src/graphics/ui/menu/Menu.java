package graphics.ui.menu;

import core.Main;
import graphics.ui.UIElement;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;
import util.DrawUtilities;

import java.util.Map;

public abstract class Menu implements UIElement {
    public int getWidth() {
        return width;
    }

    protected final int width;
    protected final int height;

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected final int x;
    protected final int y;
    protected boolean show = true;
    protected boolean initialPlay = false;
    protected Map<String, Font> fonts;

    protected Menu(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = Main.width() / 2;
        this.y = Main.height() / 2;
        this.initializeFonts();
    }

    protected Menu(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.initializeFonts();
    }

    @Override
    public void render(GameContainer gc) {
        Graphics g = gc.getGraphics();
        if (!show) return;
        g.setColor(new Color(0, 0, 0, 170));
        DrawUtilities.fillShapeCentered(g, new RoundedRectangle(0, 0, width, height, RoundedRectangle.ALL), x, y);
        g.setColor(new Color(219, 202, 106));
        DrawUtilities.drawShapeCentered(g, new RoundedRectangle(0, 0, width, height, RoundedRectangle.ALL), x, y);
        this.subrender(g);
    }

    @Override
    public void update(GameContainer gc) { }

    public void close() { this.show = false; }
    public void toggle() { this.show ^= true; }
    public void open() { this.show = true; }

    public void remove() { Main.UI.menus.remove(this); }

    protected abstract void subrender(Graphics g);
    protected abstract void initializeFonts();
}
