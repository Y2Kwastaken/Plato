package sh.miles.plato.ui.listener;

import org.jspecify.annotations.Nullable;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Represents a generic hover listener for inverting background and foreground
 */
public final class MouseHoverColorSwitch implements MouseListener {

    @Nullable
    private Color background;
    @Nullable
    private Color foreground;

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        final Component component = e.getComponent();
        this.background = component.getBackground();
        this.foreground = component.getForeground();
        component.setForeground(this.background);
        component.setBackground(this.foreground);
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        final Component component = e.getComponent();
        if (this.background != null) {
            component.setBackground(this.background);
        }

        if (this.foreground != null) {
            component.setForeground(this.foreground);
        }
    }
}
