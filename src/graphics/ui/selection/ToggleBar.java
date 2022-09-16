package graphics.ui.selection;

import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import util.Commandable;
import util.DrawUtilities;
import util.bundle.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ToggleBar {
    public ArrayList<Bundle<String, Commandable>> toggles;
    public ArrayList<Rectangle> rectangles;

    public ToggleBar(Bundle<String, Commandable>... toggles) {
        this.toggles = new ArrayList<>(List.of(toggles));
        this.rectangles = new ArrayList<>();
        for (var i = 0; i < toggles.length; i++) {
            if (i == 0 || i == toggles.length - 1)
                rectangles.add(new RoundedRectangle(0, 0, 50, 30, 10, 25,
                        i == 0 ? RoundedRectangle.TOP_LEFT + RoundedRectangle.BOTTOM_LEFT : RoundedRectangle.TOP_RIGHT + RoundedRectangle.BOTTOM_RIGHT));
            else rectangles.add(new Rectangle(0, 0, 50, 30));
        }
    }

    public void update(GameContainer gc) {}

    public void render(GameContainer gc, float x, float y) {
        var g = gc.getGraphics();
        for (var i = 0; i < toggles.size(); i++) {
            var rect = rectangles.get(i);
            rect.setX(x + 50 * i);
            rect.setY(y);
            g.draw(rect);
            DrawUtilities.drawStringCentered(g, toggles.get(i).object, Main.font, rect);
        }
    }
}
