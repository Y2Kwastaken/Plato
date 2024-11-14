package sh.miles.plato.ui;

import sh.miles.plato.Registries;
import sh.miles.plato.lang.TranslationKeys;
import sh.miles.plato.ui.pane.AbstractPane;
import sh.miles.plato.ui.pane.PaneKeys;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;

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
            pane.setVisible(false);
        });
    }

    public void show(String key) {
        Registries.PANE.get(key).ifPresentOrElse(AbstractPane::display, () -> {
            final JLabel label = new JLabel();
            label.setText("No Such PaneKey \"" + key + "\" can be found.");
            label.setForeground(Color.RED);
            add(label);
        });
    }

    public static void start() {
        SwingUtilities.invokeLater(() -> {
            final Frame frame = new Frame();
            frame.setVisible(true);
            frame.show(PaneKeys.MEAL_LIST);
        });
    }

}
