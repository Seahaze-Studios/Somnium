package graphics.ui.selection;

import core.Main;
import managers.SoundManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import util.Commandable;
import util.DrawUtilities;
import util.bundle.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ToggleBar implements Settable {
    private ArrayList<Bundle<String, Commandable>> toggles;
    private ArrayList<Rectangle> rectangles;
    private float length;
    private int toggledIndex;

    public ToggleBar(Bundle<String, Commandable>... toggles) {
        this.toggles = new ArrayList<>(List.of(toggles));
        this.rectangles = new ArrayList<>();
        var sorted = Arrays.stream(toggles).sorted((a, b) -> Main.font.getWidth(b.object) - Main.font.getWidth(a.object)).toList();
        var sortlist = new ArrayList<>(List.of(toggles));
        Collections.sort(sortlist, (a, b) -> Main.font.getWidth(b.object) - Main.font.getWidth(a.object));
        length = Main.font.getWidth(sortlist.get(0).object) + 20;
        for (var i = 0; i < toggles.length; i++) {
            if (i == 0 && toggles.length == 1) rectangles.add(new RoundedRectangle(0, 0, length, 31, 10, 25, RoundedRectangle.ALL));
            else if (i == 0 || i == toggles.length - 1)
                rectangles.add(new RoundedRectangle(0, 0, length, 31, 10, 25,
                        i == 0 ? RoundedRectangle.TOP_LEFT + RoundedRectangle.BOTTOM_LEFT : RoundedRectangle.TOP_RIGHT + RoundedRectangle.BOTTOM_RIGHT));
            else rectangles.add(new Rectangle(0, 0, length, 30));
        }
    }

    public void update(GameContainer gc) {

    }

    public void render(GameContainer gc, float x, float y) {
        var g = gc.getGraphics();
        for (var i = 0; i < toggles.size(); i++) {
            var rect = rectangles.get(i);
            var rX = rect.getCenterX();
            var width = rect.getWidth();
            var rY = rect.getCenterY();
            var height = rect.getHeight();
            var mouseX = gc.getInput().getAbsoluteMouseX();
            var mouseY = gc.getInput().getAbsoluteMouseY();
            rect.setX(x + length * i);
            rect.setY(y + 5);
            if (i == toggledIndex) {
                g.setColor(Color.white);
                g.draw(rect);
                g.fill(rect);
                g.setColor(Color.black);
            } else {
                g.setColor(Color.white);
                g.draw(rect);
                if (rX - width / 2 < mouseX && mouseX < rX + width / 2) {
                    if (rY - height / 2 < mouseY && mouseY < rY + height / 2) {
                        g.setColor(new Color(255, 255, 255, 100));
                        g.fill(rect);
                        if (gc.getInput().isMousePressed(0)) {
                            toggledIndex = i;
                            SoundManager.playSoundEffect("click");
                            toggles.get(i).element.command();
                        }
                        g.setColor(Color.white);
                    }
                }
            }
            DrawUtilities.drawStringCentered(g, toggles.get(i).object, Main.font, rect);
        }
    }

    public ToggleBar setToggledIndex(int index) {
        toggledIndex = index;
        return this;
    }
}
