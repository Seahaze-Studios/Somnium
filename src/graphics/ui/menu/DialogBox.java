package graphics.ui.menu;

import gamestates.Game;
import graphics.ui.button.Button;
import graphics.ui.UIElement;
import graphics.ui.button.CloseButton;
import managers.SoundManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import util.DrawUtilities;
import util.SomniumFont;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DialogBox extends Menu implements UIElement {
    String title;
    String body;
    List<Button> buttons;
    List<String> bodyLines = new ArrayList<>();

    public DialogBox(int width, int height, String title, String body) {
        super(width, height);
        this.title = title;
        this.body = body;
        formatBody();
    }

    public DialogBox(int width, int height, String title, String body, Button... buttons) {
        super(width, height);
        this.title = title;
        this.body = body;
        this.buttons = Arrays.asList(buttons);
        this.initializeButtons(buttons);
        formatBody();
    }

    public DialogBox(int x, int y, int width, int height, String title, String body, Button... buttons) {
        super(x, y, width, height);
        this.title = title;
        this.body = body;
        this.buttons = Arrays.asList(buttons);
        this.initializeButtons(buttons);
        formatBody();
    }

    public void initializeButtons(Button... buttons) {
        for (Button b : buttons) {
            b.setParent(this);
            if (b instanceof CloseButton cb) cb.setCommand(() -> { cb.getParent().remove(); cb.getSecondCommand().command(); });
        }
        this.buttons = List.of(buttons);
    }

    public void initializeButtons() {
        for (Button b : this.buttons) b.setParent(this);
    }

    @Override
    public void initializeFonts() {
        this.fonts = new HashMap<>();
        try {
            fonts.put("title", new SomniumFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("res/font/Aileron-Light.otf")).deriveFont(java.awt.Font.PLAIN, 50), true));
            fonts.put("body", new SomniumFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("res/font/Aileron-Light.otf")).deriveFont(java.awt.Font.PLAIN, 30), true));
        } catch (Exception ignored) {}
    }

    public void formatBody() {
        bodyLines.add(body);
    }

    @Override
    protected void subrender(Graphics g) {
        g.setColor(Color.white);
        DrawUtilities.drawStringCentered(g, title, fonts.get("title"), x, y - height / 2 + 40);
        for (var i = 0; i < bodyLines.size(); i++) {
            ((SomniumFont) fonts.get("body")).drawString(x,
                    y - fonts.get("body").getHeight(bodyLines.get(i)), bodyLines.get(i), Color.white, SomniumFont.ALIGN_CENTER);
        }
        if (buttons.size() == 1) {
            for (var i = 0; i < buttons.size(); i++) {
                buttons.get(i).setX(x / buttons.size() * (i + 1));
                buttons.get(i).setY(y + height / 2 - 40);
                buttons.get(i).render(Game.getGc());
            }
        } else {
            buttons.get(0).setX(x - 100);
            buttons.get(0).setY(y + height / 2 - 40);
            buttons.get(0).render(Game.getGc());
            buttons.get(1).setX(x + 100);
            buttons.get(1).setY(y + height / 2 - 40);
            buttons.get(1).render(Game.getGc());
        }
    }

    @Override
    public void update(GameContainer gc) {
        super.update(gc);
        if (gc.getInput().isMouseButtonDown(0)) {
            buttons.forEach(b -> {
                if (b.onButton(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
                    SoundManager.playSoundEffect("click");
                    b.getCommand().command();
                }
            });
        }
    }
}
