package graphics.ui.tabber;

import core.Main;
import graphics.ui.selection.Settable;
import graphics.ui.selection.ToggleBar;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import util.bundle.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TabBody {
    private ArrayList<Bundle<String, Settable>> options;

    public TabBody(Bundle<String, Settable>... options) {
        this.options = new ArrayList<>(List.of(options));
    }

    public void render(GameContainer gc, float x, float y, float width, float height) {
        var g = gc.getGraphics();
        for (int i = 0; i < options.size(); i++) {
            var o = options.get(i);
            g.setFont(Main.fonts.VariableWidth.P35);
            g.setColor(Color.white);
            g.drawString(o.object, x + 20, (height / 12 * i) + y + 20);
            o.element.render(gc, x + 20 + Main.fonts.VariableWidth.P35.getWidth(o.object) + 20, (height / 12 * i) + y + 20);
        }
    }
}
