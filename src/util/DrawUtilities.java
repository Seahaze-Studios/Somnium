package util;

import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public final class DrawUtilities {
    private DrawUtilities() {
        throw new UnsupportedOperationException("You cannot instantiate this utility class!");
    }

    /*================================================================*/
    /*                      CENTERING UTILITIES                       */
    /*================================================================*/
    public static Font defaultFont = Main.font;
    public static Rectangle createRectangleCentered(float x, float y, float width, float height) {
        return new Rectangle(x - width / 2, y - height / 2, width, height);
    }

    public static Shape centerShape(Shape shape, float x, float y) {
        shape.setCenterX(x);
        shape.setCenterY(y);
        return shape;
    }

    public static void drawImageCentered(Graphics g, Image image, float x, float y) {
        int width = image.getWidth();
        int height = image.getHeight();
        Rectangle r = createRectangleCentered(x, y, width, height);

        g.drawImage(image, (r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2));
    }

    /*===================================*/
    /*              Strings              */
    /*===================================*/

    public static void drawStringCentered(Graphics g, String string, float x, float y) {
        Font font = defaultFont;
        int width = font.getWidth(string);
        int height = font.getHeight(string);
        Rectangle r = createRectangleCentered(x, y, width, height);

        g.setFont(font);
        g.drawString(string, (r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2));
    }

    public static void drawStringCentered(Graphics g, String string, Image image) {
        Font font = defaultFont;
        int width = font.getWidth(string);
        int height = font.getHeight(string);
        Rectangle r = createRectangleCentered(image.getCenterOfRotationX(), image.getCenterOfRotationY(), width, height);

        g.drawString(string, (r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2));
    }


    public static void drawStringCentered(Graphics g, String string, Font font, float x, float y) {
        Font prevFont = g.getFont();
        int width = font.getWidth(string);
        int height = font.getHeight(string);
        Rectangle r = createRectangleCentered(x, y, width, height);

        g.setFont(font);
        g.drawString(string, (r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2));
        g.setFont(prevFont);
    }

    public static void drawStringCenteredByFont(Graphics g, String string, SomniumFont font, Color color, float x, float y) {
        Font prevFont = g.getFont();
        int width = font.getWidth(string);
        int height = font.getHeight(string);
        Rectangle r = createRectangleCentered(x, y, width, height);

        g.setFont(font);
        font.drawString((r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2), string, color, SomniumFont.ALIGN_CENTER);
        g.setFont(prevFont);
    }

    public static void drawStringCenteredByFont(Graphics g, String string, SomniumFont font, Color color, Rectangle r) {
        int width = font.getWidth(string);
        int height = font.getHeight(string);

        font.drawString((r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2), string, color, SomniumFont.ALIGN_CENTER);
    }

    public static void drawStringCentered(Graphics g, String string, Rectangle r) {
        Font font = defaultFont;
        int width = font.getWidth(string);
        int height = font.getHeight(string);

        g.drawString(string, (r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2));
    }

    public static void drawStringCentered(Graphics g, String string, Font font, Rectangle r) {
        int width = font.getWidth(string);
        int height = font.getHeight(string);

        g.setFont(font);
        g.drawString(string, (r.getX() + r.getWidth() / 2) - (width / 2),
                (r.getY() + r.getHeight() / 2) - (height / 2));
    }

    public static void drawShapeCentered(Graphics g, Shape shape, float x, float y) {
        centerShape(shape, x, y);
        g.draw(shape);
    }

    public static void fillShapeCentered(Graphics g, Shape shape, float x, float y) {
        centerShape(shape, x, y);
        g.fill(shape);
    }

    public static void animateLines(Graphics g, ArrayList<Line> lines, int counter, double speed) {
        for (var i = 0; i < lines.size() && i < counter; i++) {
            //var line = lines.get(i).getEnd().equals(lines.get(i).getEnd().scale(0.001f * counter)) ? lines.get(i) : new Line(lines.get(i).getStart(), lines.get(i).getEnd().scale(0.001f * counter));
            //g.draw(line);
        }
    }
}
