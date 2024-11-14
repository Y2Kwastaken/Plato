package sh.miles.plato.ui.pane;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import sh.miles.plato.ui.Frame;
import sh.miles.plato.ui.UIConstants;
import sh.miles.plato.util.collection.Registry;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A Generic AbstractPane used as a base for all other panes
 */
@NullMarked
public abstract class AbstractPane extends JPanel implements Registry.Key<String> {

    protected final GridBagConstraints constraints;
    @Nullable
    protected Frame frame;

    protected AbstractPane() {
        setLayout(new GridBagLayout());
        this.constraints = new GridBagConstraints();
        setPreferredSize(UIConstants.PREFERRED_SIZE);
        init();
    }

    /**
     * Puts a component into the pane using the global constraints of this pane
     *
     * @param comp the component to put
     */
    protected final void put(final Component comp) {
        super.add(comp, constraints);
    }

    /**
     * Puts a component into the pane using the adjusted constraints
     *
     * @param component  the components to put
     * @param adjustment the adjustments to make to global constraints
     */
    public final void put(final Component component, Consumer<GridBagConstraints> adjustment) {
        final GridBagConstraints cloned = (GridBagConstraints) this.constraints.clone();
        adjustment.accept(cloned);
        super.add(component, cloned);
    }

    /**
     * Puts a component into the pane using the adjust constraints and component
     *
     * @param component  the component to put into the frame
     * @param adjustment the adjustments to make
     * @param <T>        the type of component
     */
    public final <T extends Component> void put(final T component, BiConsumer<GridBagConstraints, T> adjustment) {
        final GridBagConstraints cloned = (GridBagConstraints) this.constraints.clone();
        adjustment.accept(cloned, component);
        super.add(component, cloned);
    }

    /**
     * Associates this pane with the main frame
     *
     * @param frame the frame to associate with
     */
    public final void associate(Frame frame) {
        this.frame = frame;
    }

    /**
     * Shows this pane and only this pane
     */
    public void display() {
        setVisible(true);
        for (final Component component : getParent().getComponents()) {
            if (component != this && component instanceof AbstractPane abstractPane) {
                abstractPane.setVisible(false);
            }
        }
    }

    public abstract void init();
}
