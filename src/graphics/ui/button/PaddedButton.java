package graphics.ui.button;

import core.Main;
import gamestates.Game;
import graphics.ui.UIElement;
import graphics.ui.menu.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;
import util.Commandable;
import util.DrawUtilities;

public class PaddedButton extends Button implements UIElement {
    private RoundedRectangle rect;

    public PaddedButton(float width, String text, Commandable command) {
        super(width, 60, text);
        this.command = command;
        rect = new RoundedRectangle(0, 0, width, 60, RoundedRectangle.ALL);
        DrawUtilities.centerShape(rect, x, y);
    }

    public PaddedButton(float width, float height, String text, Commandable command) {
        super(width, height, text);
        this.command = command;
        rect = new RoundedRectangle(0, 0, width, height, RoundedRectangle.ALL);
        DrawUtilities.centerShape(rect, x, y);
    }

    public PaddedButton(float x, float y, float width, float height, String text, Commandable command) {
        super(x, y, width, height, text);
        this.command = command;
        rect = new RoundedRectangle(x, y, width, height, RoundedRectangle.ALL);
        DrawUtilities.centerShape(rect, x, y);
    }

    // Draw the image centered
    public void render(GameContainer gc) {
        Graphics g = gc.getGraphics();
        int mouseX = gc.getInput().getMouseX();
        int mouseY = gc.getInput().getMouseY();
        if (onButton(mouseX, mouseY)) {
            g.setColor(new Color(200, 200, 200));
            g.fill(rect);
            g.setColor(Color.black);
            DrawUtilities.drawStringCentered(g, "> " + text + " <", Main.fonts.VariableWidth.P35, x, y);
        }
        else {
            g.setColor(new Color(255, 255, 255));
            g.fill(rect);
            g.setColor(Color.black);
            DrawUtilities.drawStringCentered(g, text, Main.fonts.VariableWidth.P35, x, y);
        }
        g.setColor(Color.white);
    }

    public boolean onButton(int mouseX, int mouseY) {
        if(x - width / 2 < mouseX && mouseX < x + width / 2) {
            return y - height / 2 < mouseY && mouseY < y + height / 2;
        }
        return false;
    }

    @Override
    public void update(GameContainer gc) {
        if (onButton(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
            this.onFrames++;
            if (gc.getInput().isMousePressed(0)) {
                //SoundManager.playSoundEffect("click");
                command.command();
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Commandable getCommand() {
        return command;
    }

    public Menu getParent() {
        return parent;
    }

    public PaddedButton setX(float x) {
        this.x = x;
        return this;
    }

    public PaddedButton setY(float y) {
        this.y = y;
        return this;
    }

    public PaddedButton setWidth(float width) {
        this.width = width;
        return this;
    }

    public PaddedButton setHeight(float height) {
        this.height = height;
        return this;
    }

    public PaddedButton setText(String text) {
        this.text = text;
        return this;
    }

    public PaddedButton setCommand(Commandable command) {
        this.command = command;
        return this;
    }

    public PaddedButton setParent(Menu parent) {
        this.parent = parent;
        return this;
    }
}


