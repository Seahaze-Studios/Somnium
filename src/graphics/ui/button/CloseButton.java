package graphics.ui.button;

import graphics.ui.button.Button;
import util.Commandable;

public class CloseButton extends PaddedButton {
    private Commandable secondCommand;
    public CloseButton(int x, int y, String text) {
        super(x, y, 100, 40, text);
        secondCommand = () -> {};
    }

    public CloseButton(String text, Commandable command) {
        super(text);
        secondCommand = command;
    }

    public Commandable getSecondCommand() {
        return secondCommand;
    }

    public void setSecondCommand(Commandable secondCommand) {
        this.secondCommand = secondCommand;
    }
}
