package sh.miles.plato.ui;

import sh.miles.plato.Registries;
import sh.miles.plato.lang.TranslationKeys;
import sh.miles.plato.ui.pane.AbstractPane;
import sh.miles.plato.ui.pane.PaneKeys;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Frame extends JFrame {

    private Frame() {
        setTitle(TranslationKeys.TITLE.get());
        setSize(UIConstants.PREFERRED_SIZE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

    public void init() {
        Registries.PANE.forEach((pane) -> {
            pane.associate(this);
            this.add(pane);
        });
    }

    public void show(String key) {
        Registries.PANE.get(key).ifPresent(AbstractPane::display);
    }

    public static void start() {
        SwingUtilities.invokeLater(() -> {
            final Frame frame = new Frame();
            frame.setVisible(true);
            frame.show(PaneKeys.MEAL_LIST);
        });
    }

}
