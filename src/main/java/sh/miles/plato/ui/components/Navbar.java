package sh.miles.plato.ui.components;

import org.jspecify.annotations.NullMarked;
import sh.miles.plato.lang.TranslationKeys;
import sh.miles.plato.ui.listener.MouseHoverColorSwitch;
import sh.miles.plato.ui.pane.SimplePane;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.awt.GridBagConstraints.NORTHWEST;

/**
 * Represents the general Navbar component
 */
@NullMarked
public class Navbar extends SimplePane {

    @Override
    public void init() {
        constraints.anchor = NORTHWEST;
        constraints.ipady = 10;
        put(new JLabel(TranslationKeys.NAVBAR_HOME.get()), (c, label) -> {
            label.setOpaque(true);
            label.addMouseListener(new MouseHoverColorSwitch());
        });
        put(new JPanel(), (c) -> {
            c.weightx = 1.0;
        });
    }
}
