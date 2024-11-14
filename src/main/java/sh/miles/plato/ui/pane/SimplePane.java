package sh.miles.plato.ui.pane;

import org.jspecify.annotations.NullMarked;

import java.awt.GridBagConstraints;
import java.util.function.Consumer;

/**
 * A Simple AbstractPane implementation met to be used in nested cases
 */
@NullMarked
public class SimplePane extends AbstractPane {

    @Override
    public void init() {
    }

    @Override
    public String getKey() {
        throw new UnsupportedOperationException("SimplePane can not be used within Registries");
    }

    /**
     * Modifies the global constraints for this pane
     *
     * @param constraints the constraint modifications
     */
    public void modifyConstraints(final Consumer<GridBagConstraints> constraints) {
        constraints.accept(this.constraints);
    }
}
