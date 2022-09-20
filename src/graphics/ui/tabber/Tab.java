package graphics.ui.tabber;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import util.DrawUtilities;

public class Tab {
    private Rectangle box;
    private String text;
    private TabBody body;
    private boolean active;

    public Tab(Rectangle box, String text, TabBody body) {
        this.box = box;
        this.text = text;
        this.body = body;
        this.active = false;
    }

    public void update(GameContainer gc) {
        if (mouseOver(gc)) {
            if (gc.getInput().isMousePressed(0)) this.active = true;
        }
    }

    public void render(GameContainer gc) {
        var g = gc.getGraphics();
        var prevColor = g.getColor();
        if (active) {
            g.setColor(Color.white);
            g.fill(box);
            g.setColor(Color.black);
            DrawUtilities.drawStringCentered(g, text, Main.fonts.VariableWidth.P35, box);
        } else if (mouseOver(gc)) {
            g.setColor(new Color(255, 255, 255, 100));
            g.fill(box);
            g.setColor(Color.white);
            DrawUtilities.drawStringCentered(g, text, Main.fonts.VariableWidth.P35, box);
        } else {
            g.setColor(Color.white);
            g.draw(box);
            DrawUtilities.drawStringCentered(g, text, Main.fonts.VariableWidth.P35, box);
        }
        g.setColor(prevColor);
    }

    public boolean mouseOver(GameContainer gc) {
        var mouseX = gc.getInput().getMouseX();
        var mouseY = gc.getInput().getMouseY();
        var x = this.box.getCenterX();
        var y = this.box.getCenterY();
        var width = this.box.getWidth();
        var height = this.box.getHeight();
        if(x - width / 2 < mouseX && mouseX < x + width / 2) {
            return y - height / 2 < mouseY && mouseY < y + height / 2;
        }
        return false;
    }

    public Rectangle getBox() {
        return box;
    }

    public TabBody getBody() {
        return body;
    }

    public void setBody(TabBody body) {
        this.body = body;
    }

    public boolean isActive() {
        return active;
    }

    public Tab setActive(boolean active) {
        this.active = active;
        return this;
    }
}
